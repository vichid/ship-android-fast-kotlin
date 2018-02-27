package com.example.myapplication.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nytimes.android.external.fs3.filesystem.FileSystem
import com.nytimes.android.external.fs3.filesystem.FileSystemFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    internal fun provideCacheDir(context: Context): FileSystem = FileSystemFactory.create(context.cacheDir)
}