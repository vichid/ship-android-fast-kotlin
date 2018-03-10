package com.example.myapplication

import android.os.StrictMode
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber
import timber.log.Timber.DebugTree

class MockApp : App() {

    override fun onCreate() {
        super.onCreate()
        LeakCanary.install(this@MockApp)
        enforceThreadStrictMode()
        enforceVMStrictMode()
    }

    override fun createTimberTree(): Timber.Tree = DebugTree()

    /**
     * Enforces StrictMode for the current thread.
     * @see <a href="https://developer.android.com/reference/android/os/StrictMode.html">
     *     Strict Mode | Android Developers</a>
     */
    private fun enforceThreadStrictMode() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .penaltyDialog()
            .build())
    }

    /**
     * Enforces StrictMode for the VM.
     * @see <a href="https://developer.android.com/reference/android/os/StrictMode.html">
     *     Strict Mode | Android Developers</a>
     */
    private fun enforceVMStrictMode() {
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build())
    }
}