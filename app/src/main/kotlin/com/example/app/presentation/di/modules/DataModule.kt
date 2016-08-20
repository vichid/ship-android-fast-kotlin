package com.example.app.presentation.di.modules

import android.os.Build
import com.example.app.BuildConfig
import com.example.app.data.repository.github.GithubDataRepository
import com.example.app.domain.repository.GithubRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

/**
 * A dagger module that provides all other stuff to be used in [ApiModule] or [DataModule]
 */
@Module(includes = arrayOf(ApiModule::class))
class DataModule {

    @Provides
    @Singleton
    fun provideGithubRepository(dataRepository: GithubDataRepository): GithubRepository {
        return dataRepository
    }

    @Provides
    @Singleton
    @Named("apiUrl")
    internal fun provideApiUrl(): String {
        return BuildConfig.API_URL
    }

    @Provides
    @Singleton
    @Named("userAgent")
    internal fun provideUserAgentHeader(): String {
        return String.format(
                Locale.getDefault(),
                "Android;%s;%s;%d;%s;%s;%d;",
                Build.BRAND,
                Build.MODEL,
                Build.VERSION.SDK_INT,
                BuildConfig.APPLICATION_ID,
                BuildConfig.VERSION_NAME,
                BuildConfig.VERSION_CODE)
    }

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}
