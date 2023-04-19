package com.android.zoodemo.view.plant

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.zoodemo.databinding.ItemViewBinding
import com.android.zoodemo.util.GlideUtil
import com.android.zoodemo.data.model.PlantModel

class PlantListAdapter(private val mPlantClickListener : (PlantModel) -> Unit) : RecyclerView.Adapter<PlantViewHolder>() {
    var mData = listOf<PlantModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantViewHolder(binding, mPlantClickListener)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.onBind(mData[position])
    }
}

class PlantViewHolder(private val binding: ItemViewBinding, val mPlantClickListener: (PlantModel) -> Unit): RecyclerView.ViewHolder(binding.root) {
    fun onBind(plantModel: PlantModel) {
        binding.root.setOnClickListener{ mPlantClickListener(plantModel) }
        binding.tvName.text = plantModel.fNameCh
        binding.tvInfo.text = plantModel.fAlsoKnown
        binding.tvMemo.visibility = GONE
        GlideUtil.loadImage(plantModel.fPic01URL, binding.root.context, binding.ivImage, android.R.drawable.stat_sys_download)
    }
}
