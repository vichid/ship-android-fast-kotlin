package com.example.app.presentation.utils.imageloader

import com.facebook.drawee.view.SimpleDraweeView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class FrescoImageLoader @Inject constructor() : ImageLoader {

    override fun <T> loadImage(uri: String, imageView: T) {
        when (imageView) {
            is SimpleDraweeView -> {
                imageView.setImageURI(uri)
            }
        }
    }
}