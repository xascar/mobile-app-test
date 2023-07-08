package dev.xascar.mobileapptest.model


import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("ok")
    val ok: Int
)