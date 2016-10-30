package com.example.app.presentation.di.modules

import com.example.app.data.repository.follower.FollowerDataRepository
import com.example.app.data.repository.githubuser.GithubUserDataRepository
import com.example.app.domain.repository.FollowerRepository
import com.example.app.domain.repository.GithubUserRepository
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
    internal fun provideGithubUserRepository(
            githubUserDataRepository: GithubUserDataRepository
    ): GithubUserRepository {
        return githubUserDataRepository
    }

    @Provides
    @Singleton
    internal fun provideFollowerRepository(
            followerDataRepository: FollowerDataRepository
    ): FollowerRepository {
        return followerDataRepository
    }

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}
