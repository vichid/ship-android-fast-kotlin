package com.example.myapplication.util

fun <T> unSafeLazy(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) {
        initializer()
    }