package com.example.app.presentation

import android.app.Application
import android.content.Context
import com.example.app.presentation.di.components.ApplicationComponent
import com.example.app.presentation.di.components.DaggerApplicationComponent
import com.example.app.presentation.di.modules.ApplicationModule
import com.squareup.leakcanary.LeakCanary

/**
 * Android Main Application
 */
class AndroidApplication : Application() {

    val component: ApplicationComponent
        get() = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

    override fun onCreate() {
        super.onCreate()
        initLeakCanary()
        component.inject(this)
    }

    private fun initLeakCanary() {
        LeakCanary.install(this)
    }

    companion object {

        operator fun get(context: Context): AndroidApplication {
            return context.applicationContext as AndroidApplication
        }
    }
}
