package com.example.app.data.local.strategy

import com.example.app.data.local.CachingStrategy

class NotNullCachingStrategy<T> : CachingStrategy<T> {

    override fun isValid(value: T): Boolean {
        return value != null
    }
}