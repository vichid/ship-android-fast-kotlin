package com.example.app.presentation.di.components

import com.example.app.presentation.AndroidApplication
import com.example.app.presentation.ApplicationModule
import com.example.app.presentation.di.modules.ActivityModule
import dagger.Component
import javax.inject.Singleton

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(androidApplication: AndroidApplication)

    operator fun plus(activityModule: ActivityModule): ActivityComponent
}
