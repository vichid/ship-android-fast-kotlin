package com.example.myapplication.util

import android.annotation.TargetApi
import android.os.Build
import com.example.myapplication.BuildConfig

@TargetApi(Build.VERSION_CODES.KITKAT)
inline fun KorAbove(body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        body()
    }
}

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
inline fun LorAbove(body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        body()
    }
}

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
inline fun MorAbove(body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        body()
    }
}

@TargetApi(Build.VERSION_CODES.N)
inline fun NorAbove(body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        body()
    }
}

inline fun debug(body: () -> Unit) {
    if (BuildConfig.DEBUG) {
        body()
    }
}

inline fun prod(body: () -> Unit) {
    if (!BuildConfig.DEBUG) {
        body()
    }
}