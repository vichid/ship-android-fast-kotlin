package com.example.myapplication.util.crashlibrary

class FakeCrashLibrary private constructor() {

    companion object {
        fun log(priority: Int, tag: String, message: String) {
        }

        fun logWarning(t: Throwable) {
        }

        fun logError(t: Throwable) {
        }
    }
}
