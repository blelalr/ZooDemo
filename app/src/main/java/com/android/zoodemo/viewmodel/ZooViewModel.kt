package com.android.zoodemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.android.zoodemo.data.model.AreaResponseModel
import com.android.zoodemo.data.model.PlantModel
import com.android.zoodemo.data.network.ApiResponse
import com.android.zoodemo.data.repository.ZooRepo
import kotlinx.coroutines.Dispatchers

class ZooViewModel(private val zooRepo:ZooRepo = ZooRepo()) :ViewModel() {
    val mPlantMap = MutableLiveData<Map<String, ArrayList<PlantModel>>>()
    val mAreaList = MutableLiveData<AreaResponseModel>()

    fun getAreaList(): LiveData<ApiResponse<AreaResponseModel>>{
        return liveData(Dispatchers.IO) {
            emit(zooRepo.getAreaList())
        }
    }

    fun getPlantMap(): LiveData<ApiResponse<Map<String, ArrayList<PlantModel>>>> {
        return liveData(Dispatchers.IO) {
            emit(zooRepo.getPlantMap())
        }
    }

    fun getPlantListByKey(key: String): List<PlantModel> {
        val map = mPlantMap.value
        return if(map.isNullOrEmpty()){
            listOf()
        } else {
            map.let { it.getOrElse(key, { listOf<PlantModel>()}) }
        }
    }


}