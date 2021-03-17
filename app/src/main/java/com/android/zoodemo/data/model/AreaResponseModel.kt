package com.android.zoodemo.data.model


import com.google.gson.annotations.SerializedName

data class AreaResponseModel(
    @SerializedName("result")
    val areaDataModel: AreaDataModel = AreaDataModel()
)