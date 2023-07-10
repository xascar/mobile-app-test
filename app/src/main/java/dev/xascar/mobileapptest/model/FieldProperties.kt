package dev.xascar.mobileapptest.model

import com.google.gson.annotations.SerializedName

data class FieldProperties<out T: Any>(
    @SerializedName("auto_approve")
    val autoApprove: Boolean,
    @SerializedName("cl_visible")
    val clVisible: Boolean,
    @SerializedName("clientzone_check")
    val clientzoneCheck: Any,
    @SerializedName("clientzone_editable")
    val clientzoneEditable: Boolean,
    @SerializedName("clientzone_required")
    val clientzoneRequired: Boolean,
    @SerializedName("clientzone_visible")
    val clientzoneVisible: Boolean,
    @SerializedName("condition")
    val condition: List<Any>,
    @SerializedName("condition_type")
    val conditionType: Int,
    @SerializedName("group")
    val group: String,
    @SerializedName("hidetitle")
    val hidetitle: Boolean,
    @SerializedName("mapper")
    val mapper: String,
    @SerializedName("maxlength")
    val maxlength: Int?,
    @SerializedName("newline")
    val newline: Boolean,
    @SerializedName("order")
    val order: Int,
    @SerializedName("regex")
    val regex: String?,
    @SerializedName("req")
    val req: Boolean,
    @SerializedName("split")
    val split: Boolean,
    @SerializedName("step")
    val step: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("values")
    val values: T?,
    @SerializedName("visible")
    val visible: Boolean
)