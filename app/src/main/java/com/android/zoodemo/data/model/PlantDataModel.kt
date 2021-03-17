package com.android.zoodemo.data.model


import com.google.gson.annotations.SerializedName

data class PlantDataModel(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("limit")
    val limit: Int = 0,
    @SerializedName("offset")
    val offset: Int = 0,
    @SerializedName("results")
    val plantList: List<PlantModel> = listOf(),
    @SerializedName("sort")
    val sort: String = ""
)