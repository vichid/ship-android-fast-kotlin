@file:Suppress("NOTHING_TO_INLINE")

package com.example.myapplication.util.ext

public inline fun Int?.orZero(): Int = this ?: 0
