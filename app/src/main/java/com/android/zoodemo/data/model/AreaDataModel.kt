package com.android.zoodemo.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AreaDataModel(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "results")
    val areaList: List<AreaModel> = listOf(),
    @Json(name = "limit")
    val limit: Int = 0,
    @Json(name = "offset")
    val offset: Int = 0,
    @Json(name = "sort")
    val sort: String = ""
)