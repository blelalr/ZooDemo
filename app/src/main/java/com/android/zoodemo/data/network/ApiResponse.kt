package com.android.zoodemo.data.network

sealed class ApiResponse<out T> {
    data class Success<out T >(val data: T) : ApiResponse<T>()
    data class Error(val errno: Int, val msg: String) : ApiResponse<Nothing>()
    data class Exception(val exception: java.lang.Exception) : ApiResponse<Nothing>()
}