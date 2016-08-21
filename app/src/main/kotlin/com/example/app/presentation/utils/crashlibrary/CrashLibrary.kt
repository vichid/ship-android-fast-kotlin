package com.example.app.presentation.utils.crashlibrary

interface CrashLibrary {
    fun log(priority: Int, tag: String, message: String)

    fun logWarning(t: Throwable)

    fun logError(t: Throwable)
}