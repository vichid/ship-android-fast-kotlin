package com.example.app.data.local

interface CachingStrategy<T> {
    fun isValid(value: T): Boolean
}