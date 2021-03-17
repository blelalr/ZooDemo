package com.android.zoodemo.view.plant

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.zoodemo.R
import com.android.zoodemo.data.model.PlantModel
import com.android.zoodemo.util.GlideUtil
import com.bumptech.glide.Glide

class PlantListAdapter(private val mPlantClickListener : PlantClickListener) : RecyclerView.Adapter<PlantViewHolder>() {
    var mData = listOf<PlantModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlantViewHolder(
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

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.itemView.setOnClickListener{mPlantClickListener.onPlantItemClick(mData[position])}
        holder.tvPlantName.text = mData[position].fNameCh
        holder.tvPlantInfo.text = mData[position].fAlsoKnown
        holder.tvPlantMemo.visibility = GONE
        GlideUtil.loadImage(mData[position].fPic01URL, holder.ivPlantImg, android.R.drawable.stat_sys_download)
    }
}

class PlantViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvPlantName: TextView = itemView.findViewById(R.id.tv_name)
    val tvPlantInfo: TextView = itemView.findViewById(R.id.tv_info)
    val tvPlantMemo: TextView = itemView.findViewById(R.id.tv_memo)
    val ivPlantImg: ImageView = itemView.findViewById(R.id.iv_image)
}
