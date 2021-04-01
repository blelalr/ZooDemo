package com.android.zoodemo.view.area

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.transition.Transition
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.android.zoodemo.R
import com.android.zoodemo.data.model.AreaModel
import com.android.zoodemo.databinding.ItemViewBinding
import com.android.zoodemo.util.GlideUtil

class AreaListAdapter(private val mAreaClickListener: (AreaModel) -> Unit): RecyclerView.Adapter<AreaViewHolder>() {
    var mData = listOf<AreaModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AreaViewHolder(binding, mAreaClickListener)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        holder.onBind(mData[position])
    }
}

class AreaViewHolder(private val binding: ItemViewBinding, val mAreaClickListener: (AreaModel) -> Unit): RecyclerView.ViewHolder(binding.root) {

    fun onBind(areaModel: AreaModel) {
        binding.root.setOnClickListener { mAreaClickListener(areaModel) }
        binding.tvName.text = areaModel.eName
        binding.tvInfo.text = areaModel.eInfo
        binding.tvMemo.text = if(areaModel.eMemo.isNullOrBlank()) {
            binding.root.context.getString(R.string.item_view_area_tv_no_memo)
        } else {
            areaModel.eMemo
        }

        GlideUtil.loadImage(areaModel.ePicURL, binding.root.context, binding.ivImage, android.R.drawable.stat_sys_download)
    }
}
