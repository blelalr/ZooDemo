package com.android.zoodemo.data.repository

import com.android.zoodemo.data.model.AreaResponseModel
import com.android.zoodemo.data.model.PlantModel
import com.android.zoodemo.data.network.ApiManager
import com.android.zoodemo.data.network.ApiResponse

class ZooRepo {
    private val apiManager = ApiManager
    //TODO local data

    suspend fun getAreaList(): ApiResponse<AreaResponseModel> {
        return apiManager.getAreaDataFromNetwork()
    }

    suspend fun getPlantMap(): ApiResponse<Map<String, ArrayList<PlantModel>>> {
        when(val apiResponse = apiManager.getPlantDataFromNetwork()){
            is ApiResponse.Success -> {
                val plantLocationMap = mutableMapOf<String, ArrayList<PlantModel>>()
                val plantFilterRepeatMap = mutableMapOf<String, PlantModel>()
                for(temp in apiResponse.data.plantDataModel.plantList){
                    if(!plantFilterRepeatMap.containsKey(temp.fNameCh)){
                        plantFilterRepeatMap[temp.fNameCh] = temp
                    }
                }
                for (plant in plantFilterRepeatMap) {
                    val plantLocationList = plant.value.fLocation.replace(" ", "").split("；")
                    for ( plantLocation in plantLocationList) {
                        var plantLocationMapValue = mutableListOf<PlantModel>()

                        val keyLocation = when (plantLocation) {
                            "鳥園" -> "鳥園區"
                            "兩棲爬蟲館" -> "兩棲爬蟲動物館"
                            "穿山甲館" -> "熱帶雨林室內館（穿山甲館）"
                            else -> plantLocation
                        }

                        if(plantLocationMap.containsKey(keyLocation)){
                            plantLocationMapValue = plantLocationMap.getValue(keyLocation)
                            plantLocationMapValue.add(plant.value)
                            plantLocationMap[keyLocation] = plantLocationMapValue
                        } else {
                            plantLocationMapValue.add(plant.value)
                            plantLocationMap[keyLocation] = plantLocationMapValue as ArrayList<PlantModel>
                        }
                    }
                }
                return ApiResponse.Success(plantLocationMap as Map<String, ArrayList<PlantModel>>)
            }
            is ApiResponse.Error -> {
                return ApiResponse.Error(apiResponse.errno, apiResponse.msg)
            }
            is ApiResponse.Exception -> {
                return ApiResponse.Exception(apiResponse.exception)
            }
        }

    }

}