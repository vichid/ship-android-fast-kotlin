package com.example.myapplication.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module(includes = [DataModule::class, ViewModelModule::class])
abstract class ApplicationModule {

    @Binds
    internal abstract fun bindContext(application: Application): Context
}