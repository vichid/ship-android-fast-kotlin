package com.example.myapplication.util.ext

import android.content.Context
import android.support.v4.content.ContextCompat

fun Context.safeContext(): Context =
    takeUnless { ContextCompat.isDeviceProtectedStorage(this) }?.run {
        applicationContext.let {
            ContextCompat.createDeviceProtectedStorageContext(it) ?: it
        }
    } ?: this