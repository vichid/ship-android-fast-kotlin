package com.example.app.presentation.di.modules

import com.example.app.data.repository.github.GithubDataRepository
import com.example.app.domain.repository.GithubRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module that provides all necessary at data level
 */
@Module(includes = arrayOf(ApiModule::class))
class DataModule {

    @Provides
    @Singleton
    internal fun provideGithubRepository(githubDataRepository: GithubDataRepository): GithubRepository {
        return githubDataRepository
    }

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}
