package com.android.zoodemo.view.areaList

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.android.zoodemo.R
import com.android.zoodemo.data.model.AreaModel
import com.android.zoodemo.databinding.ItemViewBinding

class AreaListAdapter :
    ListAdapter<AreaModel, AreaListAdapter.AreaViewHolder>(DiffCallback()) {
    var onItemClick: ((AreaModel) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AreaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class AreaViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if(adapterPosition != RecyclerView.NO_POSITION)
                    onItemClick?.invoke(getItem(adapterPosition))
            }
        }

        fun bind(areaModel: AreaModel) {
            binding.apply {
                tvName.text = areaModel.eName
                tvInfo.text = areaModel.eInfo
                tvMemo.text = areaModel.eMemo.ifBlank {
                    root.context.getString(R.string.item_view_area_tv_no_memo)
                }
                ivImage.load(areaModel.ePicURL) {
                    crossfade(true)
                }
            }

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<AreaModel>() {
        override fun areItemsTheSame(oldItem: AreaModel, newItem: AreaModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AreaModel, newItem: AreaModel): Boolean {
            return oldItem == newItem
        }

    }

    companion object {
        private const val TAG = "AreaListAdapter"
    }
}