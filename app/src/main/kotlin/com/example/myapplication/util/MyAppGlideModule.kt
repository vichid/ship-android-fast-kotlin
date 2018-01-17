package com.example.myapplication.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888
import com.bumptech.glide.load.DecodeFormat.PREFER_RGB_565
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.activityManager

@GlideModule
class MyAppGlideModule : AppGlideModule() {

    @SuppressLint("NewApi")
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)

        builder.setDefaultRequestOptions(RequestOptions().format(PREFER_ARGB_8888))

        KorAbove {
            builder.setDefaultRequestOptions(RequestOptions().format(
                if (context.activityManager.isLowRamDevice) PREFER_RGB_565 else PREFER_ARGB_8888
            ))
        }

        debug {
            builder.setLogLevel(Log.DEBUG)
        }
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}