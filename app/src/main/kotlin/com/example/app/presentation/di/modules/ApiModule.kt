package com.example.app.presentation.di.modules

import android.content.Context
import android.os.Build
import com.example.app.BuildConfig
import com.example.app.data.net.github.GithubService
import com.example.app.data.net.interceptor.UserAgentInterceptor
import com.example.app.domain.executor.ThreadExecutor
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
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
    internal fun provideOkHttpClient(
            @Named("userAgent") userAgentValue: String
    ): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .addNetworkInterceptor(UserAgentInterceptor(userAgentValue))
                .build()
    }

    @Provides
    @Singleton
    internal fun provideImagePipelineConfig(
            context: Context,
            okHttpClient: OkHttpClient
    ): ImagePipelineConfig {
        return OkHttpImagePipelineConfigFactory
                .newBuilder(context, okHttpClient)
                .build();
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
    @Named("apiUrl")
    internal fun provideApiUrl(): String {
        return BuildConfig.API_URL
    }

    companion object {
        private val CONNECT_TIMEOUT_SECONDS: Long = 60;
        private val READ_TIMEOUT_SECONDS: Long = 60;
    }
}
