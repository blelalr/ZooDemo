package com.android.zoodemo.data.network

import com.android.zoodemo.data.model.AreaResponseModel
import com.android.zoodemo.data.model.PlantResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ApiManager {
    private val apiService = ApiServiceBuilder().apiService

    suspend fun getAreaDataFromNetwork(): ApiResponse<AreaResponseModel> {
        return withContext(Dispatchers.IO) {
            val result = apiService.getAreaDataService()
            try {
                if (result.isSuccessful) {
                    ApiResponse.Success(result.body() as AreaResponseModel)
                } else {
                    ApiResponse.Error(result.code(), result.message())
                }
            } catch (e: Exception) {
                ApiResponse.Exception(e)
            }
        }
    }

    suspend fun getPlantDataFromNetwork(): ApiResponse<PlantResponseModel> {
        return withContext(Dispatchers.IO) {
            val result = apiService.getPlantDataService()
            try {
                if (result.isSuccessful) {
                    ApiResponse.Success(result.body() as PlantResponseModel)
                } else {
                    ApiResponse.Error(result.code(), result.message())
                }
            } catch (e: Exception) {
                ApiResponse.Exception(e)
            }
        }
    }

}