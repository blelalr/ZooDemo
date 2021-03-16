package com.android.zoodemo.data.network

import com.android.zoodemo.data.model.AreaResponseModel
import com.android.zoodemo.data.model.PlantResponseModel
import retrofit2.Response
import retrofit2.http.GET

@JvmSuppressWildcards
interface ApiService {

    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    suspend fun getAreaDataService(): Response<AreaResponseModel>


    @GET("api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14")
    suspend fun getPlantDataService(): Response<PlantResponseModel>

}
