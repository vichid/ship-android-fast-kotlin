package com.example.myapplication.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiModule::class, StoreModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .create()
}