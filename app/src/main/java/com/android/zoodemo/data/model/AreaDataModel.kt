package com.android.zoodemo.data.model


import com.google.gson.annotations.SerializedName

data class AreaDataModel(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("limit")
    val limit: Int = 0,
    @SerializedName("offset")
    val offset: Int = 0,
    @SerializedName("results")
    val areaList: List<AreaModel> = listOf(),
    @SerializedName("sort")
    val sort: String = ""
)