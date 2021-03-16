package com.android.zoodemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.android.zoodemo.data.model.AreaResponseModel
import com.android.zoodemo.data.model.PlantModel
import com.android.zoodemo.data.network.ApiResponse
import com.android.zoodemo.data.repository.ZooRepo
import kotlinx.coroutines.Dispatchers

class ZooViewModel(private val zooRepo:ZooRepo = ZooRepo()) :ViewModel() {

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

//    fun getPlantListByKey(key: String): LiveData<ArrayList<PlantModel>>{
//        return liveData(Dispatchers.IO) {
//            emit(zooRepo.getPlantListByKey(key))
//        }
//    }


}