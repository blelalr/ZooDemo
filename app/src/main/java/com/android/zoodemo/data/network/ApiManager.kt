package com.android.zoodemo.data.network

import com.android.zoodemo.data.model.AreaResponseModel
import com.android.zoodemo.data.model.PlantResponseModel

object ApiManager {
    private val apiService = ApiServiceBuilder().apiService

    suspend fun getAreaDataFromNetwork(): ApiResponse<AreaResponseModel> {
        return try {
            val result = apiService.getAreaDataService()
            if (result.isSuccessful && result.body() != null) {
                ApiResponse.Success(result.body() as AreaResponseModel)
            } else {
                ApiResponse.Error(result.code(), result.message())
            }
        } catch (e: Exception) {
            ApiResponse.Exception(e)
        }
    }

    suspend fun getPlantDataFromNetwork(): ApiResponse<PlantResponseModel> {
        return try {
            val result = apiService.getPlantDataService()
            if (result.isSuccessful && result.body() != null) {
                ApiResponse.Success(result.body() as PlantResponseModel)
            } else {
                ApiResponse.Error(result.code(), result.message())
            }
        } catch (e: Exception) {
            ApiResponse.Exception(e)
        }
    }

}