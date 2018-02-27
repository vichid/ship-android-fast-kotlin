package com.example.myapplication.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("android:roundedImg")
    fun setRoundedImage(view: ImageView, url: String) {
        GlideApp.with(view.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .transform(CircleCrop())
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(DrawableImageViewTarget(view))
    }
}
