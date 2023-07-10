package dev.xascar.mobileapptest.domain

import android.text.InputFilter
import android.text.SpannableStringBuilder
import android.util.Log
import dev.xascar.mobileapptest.model.DataX
import dev.xascar.mobileapptest.model.FieldProperties
import dev.xascar.mobileapptest.model.Values


data class RegistrationFormDomain<out T : Any>(
    val fieldName: String,
    val type: String, // text, select, checkbox
    val visible: Boolean,
    val maxLength: Int?,
    val regex: String,
    val values: List<String>,
    val order: Int,
    val description: String
)

fun DataX.mapToDomain(): List<RegistrationFormDomain<Any>> {

    val registrationFormDomainList =  mutableListOf<RegistrationFormDomain<Any>>()

    registrationFormDomainList.addFieldProperty(amlCheck, "amlCheck")
    registrationFormDomainList.addFieldProperty(bankIban, "bankIban")
    registrationFormDomainList.addFieldProperty(customerBirthday, "Birthday")
    registrationFormDomainList.addFieldProperty(customerEmail, "e-mail")
    registrationFormDomainList.addFieldProperty(customerFirstname, "First name")
    registrationFormDomainList.addFieldProperty(customerGender, "Gender")
    registrationFormDomainList.addFieldProperty(customerLastname, "Last name")
    registrationFormDomainList.addFieldProperty(customerMonthlyIncome, "Monthly income")
    registrationFormDomainList.addFieldProperty(customerPersoncode, "Person code")
    registrationFormDomainList.addFieldProperty(customerPhone, "Phone")
    registrationFormDomainList.addFieldProperty(language, "Language")
    registrationFormDomainList.addFieldProperty(pepStatus, "PepStatus")

    return registrationFormDomainList
}

fun MutableList<RegistrationFormDomain<Any>>.addFieldProperty(element: FieldProperties<Any>, description: String): Boolean{
    return this.add(
        RegistrationFormDomain(
            fieldName = this.toString(),
            type = element.type,
            visible = element.visible,
            maxLength = element.maxlength,
            regex = element.regex ?: "",
            values = getItemsFromValue(element.values),
            description = description,
            order = element.order
        )
    )
}

fun getRegexFilter(regexPattern: String): InputFilter {
    return InputFilter { source, start, end, dest, dstart, dend ->
        try {
            val input = StringBuilder(dest)
                .replace(dstart, dend, source.subSequence(start, end).toString())
                .toString()

            Log.d("getRegexFilter", "getRegexFilter (${input.length}): $regexPattern")

            val newText = dest.subSequence(0, dstart).toString() + input +
                    dest.subSequence(dend, dest.length).toString()

            if (!newText.matches(regexPattern.toRegex())) {
                if (source is SpannableStringBuilder) {
                    source.clear()
                } else {
                    return@InputFilter ""
                }
            }
        } catch (e: Exception) {
            Log.d("getRegexFilter", "exception: $e")
        }
        null
    }
}

fun getItemsFromValue(values: Any?): List<String>{
    return when (values) {
        is List<*> -> {
            val stringList = values.mapNotNull { it as? String }
            if (stringList.size == values.size) {
                stringList
            }else {
                emptyList()
            }
        }
        is Values -> {
            listOf(values.lv,values.ru)
        }
        else -> {
            emptyList()
        }
    }
}