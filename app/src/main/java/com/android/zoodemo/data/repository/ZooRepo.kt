package com.android.zoodemo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.zoodemo.data.model.AreaResponseModel
import com.android.zoodemo.data.model.PlantModel
import com.android.zoodemo.data.network.ApiConstant
import com.android.zoodemo.data.network.ApiService
import com.android.zoodemo.view.areaDetail.PlantPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ZooRepo @Inject constructor (private val apiService: ApiService) {
    suspend fun fetchAreaData(): Flow<AreaResponseModel> {
        return flow {
            emit(apiService.getAreaData())
        }
    }

    fun fetchPlantData(areaName: String): Flow<PagingData<PlantModel>> {
       return Pager(
            config = PagingConfig(
                pageSize = ApiConstant.PLANT_LOAD_SIZE,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PlantPagingSource(apiService , areaName) }
        ).flow
    }
//    "鳥園" -> "鳥園區"
//    "兩棲爬蟲館" -> "兩棲爬蟲動物館"
//    "穿山甲館" -> "熱帶雨林室內館（穿山甲館）"

}