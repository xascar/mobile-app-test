package dev.xascar.mobileapptest.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("new-registration")
    val newRegistration: Boolean
)