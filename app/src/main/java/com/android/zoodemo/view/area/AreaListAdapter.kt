package com.android.zoodemo.view.area

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.zoodemo.R
import com.android.zoodemo.data.model.AreaModel
import com.android.zoodemo.util.GlideUtil
import com.bumptech.glide.Glide

class AreaListAdapter(private val mAreaClickListener : AreaClickListener): RecyclerView.Adapter<AreaViewHolder>() {
    var mData = listOf<AreaModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AreaViewHolder(
            layoutInflater.inflate(
                R.layout.item_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        holder.itemView.setOnClickListener{ mAreaClickListener.onAreaItemClick(mData[position])}
        holder.tvAreaName.text = mData[position].eName
        holder.tvAreaInfo.text = mData[position].eInfo
        holder.tvAreaMemo.text = if(mData[position].eMemo.isNullOrBlank()) {
            holder.tvAreaMemo.context.getString(R.string.item_view_area_tv_no_memo)
        } else {
            mData[position].eMemo
        }
        GlideUtil.loadImage(mData[position].ePicURL, holder.ivAreaImg, android.R.drawable.stat_sys_download)

    }

}

class AreaViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
    val tvAreaName: TextView = itemView.findViewById(R.id.tv_name)
    val tvAreaInfo: TextView = itemView.findViewById(R.id.tv_info)
    val tvAreaMemo: TextView = itemView.findViewById(R.id.tv_memo)
    val ivAreaImg: ImageView = itemView.findViewById(R.id.iv_image)

}
