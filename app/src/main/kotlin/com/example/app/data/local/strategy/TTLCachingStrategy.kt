package com.example.app.data.local.strategy

import com.example.app.data.local.CachingStrategy
import java.util.concurrent.TimeUnit

class TTLCachingStrategy(ttl: Long, timeUnit: TimeUnit) : CachingStrategy<Long> {

    private val ttlMillis: Long

    init {
        this.ttlMillis = timeUnit.toMillis(ttl)
    }

    override fun isValid(value: Long): Boolean {
        return value + ttlMillis > System.currentTimeMillis()
    }
}
