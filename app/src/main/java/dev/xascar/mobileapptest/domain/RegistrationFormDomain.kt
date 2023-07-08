package dev.xascar.mobileapptest.domain

import android.util.Log
import dev.xascar.mobileapptest.model.DataX
import dev.xascar.mobileapptest.model.FieldProperties


data class RegistrationFormDomain<out T : Any>(
    val fieldName: String,
    val type: String, // text, select, checkbox
    val visible: Boolean,
    val maxLength: Int,
    val regex: String,
    val values: T?,
    val order: Int,
    val text: String // for description
)

fun DataX.mapToDomain(): List<RegistrationFormDomain<Any>> {

    val registrationFormDomainList =  mutableListOf<RegistrationFormDomain<Any>>()

    registrationFormDomainList.addFieldProperty(amlCheck)
    registrationFormDomainList.addFieldProperty(bankIban)
    registrationFormDomainList.addFieldProperty(customerBirthday)
    registrationFormDomainList.addFieldProperty(customerEmail)
    registrationFormDomainList.addFieldProperty(customerFirstname)
    registrationFormDomainList.addFieldProperty(customerGender)
    registrationFormDomainList.addFieldProperty(customerLastname)
    registrationFormDomainList.addFieldProperty(customerMonthlyIncome)
    registrationFormDomainList.addFieldProperty(customerPersoncode)
    registrationFormDomainList.addFieldProperty(customerPhone)
    registrationFormDomainList.addFieldProperty(language)
    registrationFormDomainList.addFieldProperty(pepStatus)

    return registrationFormDomainList
}

fun MutableList<RegistrationFormDomain<Any>>.addFieldProperty(element: FieldProperties<Any>): Boolean{
    return this.add(
        RegistrationFormDomain(
            fieldName = this.toString(),
            type = element.type,
            visible = element.visible,
            maxLength = element.maxlength ?: 0,
            regex = element.regex ?: "",
            values = element.values,
            text = "",
            order = element.order
        )
    )
}
