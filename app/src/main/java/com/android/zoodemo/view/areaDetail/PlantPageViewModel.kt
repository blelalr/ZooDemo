package com.android.zoodemo.view.areaDetail

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.android.zoodemo.data.model.PlantModel
import com.android.zoodemo.data.repository.ZooRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PlantPageViewModel @Inject constructor(private val zooRepo: ZooRepo): ViewModel() {

    fun getPlantPagingDataByAreaName(areaName: String): Flow<PagingData<PlantModel>> {
        return zooRepo.fetchPlantData(areaName)
    }
}