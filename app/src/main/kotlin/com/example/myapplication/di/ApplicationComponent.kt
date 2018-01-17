package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    ApplicationBindingModule::class,
    AndroidSupportInjectionModule::class
])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(instance: App)

    fun userManager(): UserManager

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}