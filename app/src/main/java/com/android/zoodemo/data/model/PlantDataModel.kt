package com.android.zoodemo.data.model


import com.squareup.moshi.Json

data class PlantDataModel(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "limit")
    val limit: Int = 0,
    @Json(name = "offset")
    val offset: Int = 0,
    @Json(name = "results")
    val plantList: List<PlantModel> = listOf(),
    @Json(name = "sort")
    val sort: String = ""
)