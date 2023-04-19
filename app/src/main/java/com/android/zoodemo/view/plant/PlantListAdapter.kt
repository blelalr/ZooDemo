package com.android.zoodemo.view.plant

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.zoodemo.databinding.ItemViewBinding
import com.android.zoodemo.data.model.PlantModel

class PlantListAdapter(private val mPlantClickListener : (PlantModel) -> Unit) : PagingDataAdapter<PlantModel, PlantViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantViewHolder(binding, mPlantClickListener)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {

        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.onBind(currentItem)
        }
    }
}

class PlantViewHolder(private val binding: ItemViewBinding, val mPlantClickListener: (PlantModel) -> Unit): RecyclerView.ViewHolder(binding.root) {
    fun onBind(plantModel: PlantModel) {
        binding.root.setOnClickListener{ mPlantClickListener(plantModel) }
        binding.tvName.text = plantModel.fNameCh
        binding.tvInfo.text = plantModel.fAlsoKnown
        binding.tvMemo.visibility = GONE
        binding.ivImage.load(plantModel.fPic01URL)
    }
}

class DiffCallBack : DiffUtil.ItemCallback<PlantModel>() {
    override fun areItemsTheSame(oldItem: PlantModel, newItem: PlantModel) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PlantModel, newItem: PlantModel) =
        oldItem == newItem
}

