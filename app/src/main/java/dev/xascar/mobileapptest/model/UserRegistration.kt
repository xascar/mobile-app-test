package dev.xascar.mobileapptest.model


import com.google.gson.annotations.SerializedName

data class UserRegistration(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String
)