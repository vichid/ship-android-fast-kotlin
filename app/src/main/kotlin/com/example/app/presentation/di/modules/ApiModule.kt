package com.example.app.presentation.di.modules

import android.content.Context
import com.example.app.BuildConfig
import com.example.app.data.net.github.GithubService
import com.example.app.domain.executor.ThreadExecutor
import com.example.app.presentation.di.qualifiers.ClientCache
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.schedulers.Schedulers
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

/**
 * A dagger module that provides retrofit services
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun provideGithubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
            client: OkHttpClient,
            @Named("apiUrl")
            endPoint: String,
            moshi: Moshi,
            threadExecutor: ThreadExecutor
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(
                        RxJavaCallAdapterFactory.createWithScheduler(
                                Schedulers.from(threadExecutor)
                        )
                )
                .client(client)
                .validateEagerly(BuildConfig.DEBUG)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(@ClientCache cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .build()
    }

    @Provides
    @Singleton
    @ClientCache
    internal fun provideCache(@ClientCache path: File): Cache {
        return Cache(path, CACHE_SIZE_20MB.toLong())
    }

    @Singleton
    @Provides
    @ClientCache
    internal fun provideCacheFile(context: Context): File {
        return File(context.cacheDir, "HttpResponseCache")
    }

    companion object {
        private val CACHE_SIZE_20MB = 20 * 1024 * 1024
    }
}
