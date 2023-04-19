package com.android.zoodemo.data.network

sealed class NetworkState<out T> {
    object Idle : NetworkState<Nothing>()
    object Loading: NetworkState<Nothing>()
    data class Success<out T>(val data: T) :NetworkState<T>()
    data class Error(val errorMessage: String): NetworkState<Nothing>()
}
