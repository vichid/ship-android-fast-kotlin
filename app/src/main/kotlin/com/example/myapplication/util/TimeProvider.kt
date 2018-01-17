package com.example.myapplication.util

import org.threeten.bp.Instant

object TimeProvider {
    fun currentTimeMillis(): Long = Instant.now().toEpochMilli()
}