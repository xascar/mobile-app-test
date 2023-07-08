package dev.xascar.mobileapptest.model


import com.google.gson.annotations.SerializedName

data class Values(
    @SerializedName("lv")
    val lv: String,
    @SerializedName("ru")
    val ru: String
)