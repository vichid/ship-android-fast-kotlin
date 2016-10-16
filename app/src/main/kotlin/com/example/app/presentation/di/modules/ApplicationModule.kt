package com.example.app.presentation.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.app.data.executor.JobExecutor
import com.example.app.domain.executor.PostExecutionThread
import com.example.app.domain.executor.ThreadExecutor
import com.example.app.presentation.AndroidApplication
import com.example.app.presentation.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module(includes = arrayOf(DataModule::class, PresentationModule::class))
class ApplicationModule(private val androidApplication: AndroidApplication) {

    @Provides
    @Singleton
    internal fun application(): AndroidApplication {
        return androidApplication
    }

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return androidApplication.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferences(): SharedPreferences {
        return androidApplication.getSharedPreferences("app", Context.MODE_APPEND)
    }
}
