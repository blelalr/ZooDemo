package com.android.zoodemo.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.android.zoodemo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target



class GlideUtil {

    companion object {

        private val options = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.IMMEDIATE).format(DecodeFormat.PREFER_RGB_565)
        private val reSizeOption = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.IMMEDIATE).format(DecodeFormat.PREFER_RGB_565).override(100,100).encodeQuality(50)


        private val drawableListener = object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                // important to return false so the error placeholder can be placed
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                // important to return false so the error placeholder can be placed
                return false
            }
        }

        fun load(url: String, imageView: ImageView, placeHolder : Int) {
            Glide.with(imageView)
                .load(url)
                .apply(options)
                .error(R.color.color_A8A8A8)
                .placeholder(placeHolder)
                .listener(drawableListener)
                .centerCrop()
                .into(imageView)
        }


        fun loadImage(url: String, imageView: ImageView, placeHolder : Int) {
            Glide.with(imageView)
                    .load(url)
                    .apply(reSizeOption)
                    .error(R.color.color_A8A8A8)
                    .placeholder(placeHolder)
                    .listener(drawableListener)
                    .centerCrop()
                    .into(imageView)
        }


    }
}