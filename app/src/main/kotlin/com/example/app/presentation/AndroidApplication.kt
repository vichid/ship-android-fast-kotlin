package com.example.app.presentation

import android.app.Application
import android.content.Context
import com.example.app.presentation.di.components.ApplicationComponent
import com.example.app.presentation.di.components.DaggerApplicationComponent
import com.example.app.presentation.di.modules.ApplicationModule
import javax.inject.Inject

/**
 * Android Main Application
 */
class AndroidApplication : Application() {

    @Inject
    lateinit var appManager: AppManager

    val component: ApplicationComponent
        get() = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        appManager.init()
    }

    companion object {

        operator fun get(context: Context): AndroidApplication {
            return context.applicationContext as AndroidApplication
        }
    }
}
