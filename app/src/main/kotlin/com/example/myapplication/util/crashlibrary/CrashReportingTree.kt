package com.example.myapplication.util.crashlibrary

import android.support.annotation.NonNull
import android.util.Log
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, @NonNull message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        FakeCrashLibrary.log(priority, tag ?: "", message)

        t?.let {
            when (priority) {
                Log.ERROR -> {
                    FakeCrashLibrary.logError(t)
                }
                Log.WARN -> {
                    FakeCrashLibrary.logWarning(t)
                }
            }
        }
    }
}