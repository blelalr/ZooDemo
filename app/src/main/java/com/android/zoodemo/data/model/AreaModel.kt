package com.android.zoodemo.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AreaModel(
    @SerializedName("e_category")
    val eCategory: String = "",
    @SerializedName("e_geo")
    val eGeo: String = "",
    @SerializedName("e_info")
    val eInfo: String = "",
    @SerializedName("e_memo")
    val eMemo: String = "",
    @SerializedName("e_name")
    val eName: String = "",
    @SerializedName("e_no")
    val eNo: String = "",
    @SerializedName("e_pic_url")
    val ePicURL: String = "",
    @SerializedName("e_url")
    val eURL: String = "",
    @SerializedName("_id")
    val id: Int = 0
): Serializable