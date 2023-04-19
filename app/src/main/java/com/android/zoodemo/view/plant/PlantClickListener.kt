package com.android.zoodemo.view.plant
import com.android.zoodemo.data.model.PlantModel

interface PlantClickListener {
    fun onPlantItemClick(plant: PlantModel)
}
