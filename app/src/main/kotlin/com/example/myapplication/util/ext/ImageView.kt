package com.example.myapplication.util.ext

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.example.myapplication.util.DevError
import com.example.myapplication.util.GlideApp
import com.example.myapplication.util.GlideRequest

fun ImageView.load(url: String?) {
    load(this, url)
}

fun ImageView.load(url: String?, transformationType: TransformationType) {
    load(this, url, transformationType)
}

@SuppressLint("CheckResult")
@JvmName("privateLoad")
private fun load(view: ImageView,
    url: String?,
    transformationType: TransformationType = TransformationType.NOTHING) {
    val glideRequest: GlideRequest<Drawable> = GlideApp.with(view.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade(200))
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    if (transformationType != TransformationType.NOTHING) {
        glideRequest.transform(transformationType.getTransformation())
    }
    glideRequest.into(DrawableImageViewTarget(view))
}

fun ImageView.clear() {
    GlideApp.with(context)
        .clear(this)
}

enum class TransformationType {
    CIRCLE,
    ROUND,
    NOTHING;

    fun getTransformation(): Transformation<Bitmap> = when (this) {
        CIRCLE -> CircleCrop()
        ROUND -> RoundedCorners(20)
        else -> throw DevError()
    }
}
