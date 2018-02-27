package com.example.myapplication.di

import android.app.Application
import android.content.Context
import com.example.myapplication.AppSchedulers
import com.example.myapplication.ExecutionSchedulers
import dagger.Binds
import dagger.Module

@Module(includes = [DataModule::class])
abstract class ApplicationModule {

    @Binds
    internal abstract fun bindContext(application: Application): Context

    @Binds
    internal abstract fun bindExecutionSchedulers(appSchedulers: AppSchedulers): ExecutionSchedulers
}