package com.example.myapplication.di

import com.example.myapplication.AppSchedulers
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.api.GithubService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.tag("Retrofit").d(message) })
            .apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }

    @Provides
    internal fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    internal fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.GITHUB_URL)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .client(client)
        .validateEagerly(BuildConfig.DEBUG)
        .build()

    @Provides
    @Singleton
    internal fun provideConverterFactory(gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    internal fun provideCallAdapterFactory(
        appSchedulers: AppSchedulers
    ): CallAdapter.Factory = RxJava2CallAdapterFactory.createWithScheduler(appSchedulers.io)

    @Provides
    @Singleton
    internal fun provideGithubService(retrofit: Retrofit): GithubService =
        retrofit.create(GithubService::class.java)
}