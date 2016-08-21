package com.example.app.presentation.utils.imageloader

/**
 * Interface to be implemented to load images into the app
 */
interface ImageLoader {

    /**
     * Load an image
     * @param uri path to resource, url, ...
     * *
     * @param imageView The target ImageView or similar to load the image into.
     */
    fun <T> loadImage(uri: String, imageView: T)
}
