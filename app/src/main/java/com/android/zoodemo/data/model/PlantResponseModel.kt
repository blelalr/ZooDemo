package com.android.zoodemo.data.model


import com.squareup.moshi.Json

data class PlantResponseModel(
    @Json(name = "result")
    val plantDataModel: PlantDataModel = PlantDataModel()
)