package com.android.zoodemo.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AreaModel(
    @Json(name = "E_Category")
    val eCategory: String = "",
    @Json(name = "E_Geo")
    val eGeo: String = "",
    @Json(name = "E_Info")
    val eInfo: String = "",
    @Json(name = "E_Memo")
    val eMemo: String = "",
    @Json(name = "E_Name")
    val eName: String = "",
    @Json(name = "E_no")
    val eNo: String = "",
    @Json(name = "E_Pic_URL")
    val ePicURL: String = "",
    @Json(name = "E_URL")
    val eURL: String = "",
    @Json(name = "_id")
    val id: Int = 0
)