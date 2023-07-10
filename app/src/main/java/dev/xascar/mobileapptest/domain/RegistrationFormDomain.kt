package dev.xascar.mobileapptest.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.xascar.mobileapptest.model.DataX
import dev.xascar.mobileapptest.model.FieldProperties
import dev.xascar.mobileapptest.model.Values

@Entity
data class RegistrationFormDomain(
    @PrimaryKey val fieldName: String,
    @ColumnInfo(name = "type") val type: String, // text, select, checkbox
    @ColumnInfo(name = "visible") val visible: Boolean,
    @ColumnInfo(name = "max_length") val maxLength: Int?,
    @ColumnInfo(name = "regex") val regex: String,
    @ColumnInfo(name = "values") val values: String,
    @ColumnInfo(name = "order") val order: Int,
    @ColumnInfo(name = "description") val description: String
)

fun DataX.mapToDomain(): List<RegistrationFormDomain> {

    val registrationFormDomainList =  mutableListOf<RegistrationFormDomain>()

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

fun MutableList<RegistrationFormDomain>.addFieldProperty(element: FieldProperties<Any>, description: String): Boolean{
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

fun getItemsFromValue(values: Any?): String{
    return when (values) {
        is List<*> -> {
            val stringList = values.mapNotNull { it as? String }
            if (stringList.size == values.size) {
                stringList.toString()
            }else {
                emptyList<String>().toString()
            }
        }
        is Values -> {
            listOf(values.lv,values.ru).toString()
        }
        else -> {
            emptyList<String>().toString()
        }
    }
}