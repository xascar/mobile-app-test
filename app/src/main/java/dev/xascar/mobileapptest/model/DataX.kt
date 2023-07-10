package dev.xascar.mobileapptest.model

import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("aml-check")
    val amlCheck: FieldProperties<Any>,
    @SerializedName("bank-iban")
    val bankIban: FieldProperties<Any>,
    @SerializedName("customer-birthday")
    val customerBirthday: FieldProperties<Any>,
    @SerializedName("customer-email")
    val customerEmail: FieldProperties<Any>,
    @SerializedName("customer-firstname")
    val customerFirstname: FieldProperties<Any>,
    @SerializedName("customer-gender")
    val customerGender: FieldProperties<Any>,
    @SerializedName("customer-lastname")
    val customerLastname: FieldProperties<Any>,
    @SerializedName("customer-monthly-income")
    val customerMonthlyIncome: FieldProperties<Any>,
    @SerializedName("customer-personcode")
    val customerPersoncode: FieldProperties<Any>,
    @SerializedName("customer-phone")
    val customerPhone: FieldProperties<Any>,
    @SerializedName("language")
    val language: FieldProperties<Any>,
    @SerializedName("pep-status")
    val pepStatus: FieldProperties<Any>
)