package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.api.GithubService
import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.ghrepositories.model.GHSearchResponse
import com.example.myapplication.ghrepositories.usecase.RetrieveUserRepositoriesUseCase
import com.example.myapplication.ghrepositories.usecase.RetrieveUserRepositoriesUseCase.Companion.RETRIEVE_USER_REPOSITORIES_STORE
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase.Companion.SEARCH_REPOSITORIES_STORE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nytimes.android.external.fs3.FileSystemRecordPersister
import com.nytimes.android.external.fs3.filesystem.FileSystemFactory
import com.nytimes.android.external.store3.base.impl.Store
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.nytimes.android.external.store3.middleware.GsonParserFactory
import dagger.Module
import dagger.Provides
import okhttp3.ResponseBody
import okio.BufferedSource
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class StoreModule {

    @Provides
    @Singleton
    @Named(RETRIEVE_USER_REPOSITORIES_STORE)
    internal fun provideUserRepositoriesStore(
        context: Context,
        gson: Gson,
        githubService: GithubService
    ): Store<List<GHRepository>, RetrieveUserRepositoriesUseCase.Params> =
        StoreBuilder.parsedWithKey<RetrieveUserRepositoriesUseCase.Params, BufferedSource, List<GHRepository>>()
            .fetcher { githubService.fetchUserRepositories(it.login).map(ResponseBody::source) }
            .persister(FileSystemRecordPersister.create(
                FileSystemFactory.create(context.cacheDir),
                { RETRIEVE_USER_REPOSITORIES_STORE + "_" + it },
                5,
                TimeUnit.HOURS
            ))
            .parser(GsonParserFactory.createSourceParser(gson, object : TypeToken<List<GHRepository>>() {}.type))
            .open()

    @Provides
    @Singleton
    @Named(SEARCH_REPOSITORIES_STORE)
    internal fun provideSearchRepositoriesStore(
        context: Context,
        gson: Gson,
        githubService: GithubService
    ): Store<GHSearchResponse, SearchRepositoriesUseCase.Params> =
        StoreBuilder.parsedWithKey<SearchRepositoriesUseCase.Params, BufferedSource, GHSearchResponse>()
            .fetcher { githubService.searchRepositories(it.q, it.page, it.sort, it.ord).map(ResponseBody::source) }
            .persister(FileSystemRecordPersister.create(
                FileSystemFactory.create(context.cacheDir),
                { SEARCH_REPOSITORIES_STORE + "_" + it },
                5,
                TimeUnit.HOURS
            ))
            .parser(GsonParserFactory.createSourceParser(gson, GHSearchResponse::class.java))
            .open()
}