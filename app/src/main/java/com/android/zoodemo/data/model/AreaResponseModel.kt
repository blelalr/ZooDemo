package com.android.zoodemo.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AreaResponseModel(
    @Json(name = "result")
    val areaDataModel: AreaDataModel = AreaDataModel()
)