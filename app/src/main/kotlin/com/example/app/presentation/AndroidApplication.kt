package com.example.app.presentation

import android.app.Application
import com.example.app.presentation.di.components.ApplicationComponent
import com.example.app.presentation.di.components.DaggerApplicationComponent
import com.example.app.presentation.di.modules.ApplicationModule
import javax.inject.Inject

/**
 * Android Main Application
 */
class AndroidApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    @Inject
    lateinit var appManager: AppManager

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        appManager.init()
    }
}
