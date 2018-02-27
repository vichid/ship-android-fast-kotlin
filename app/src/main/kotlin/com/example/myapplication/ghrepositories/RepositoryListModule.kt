package com.example.myapplication.ghrepositories

import com.example.myapplication.data.api.GithubService
import com.example.myapplication.ghrepositories.model.GHSearchResponse
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase
import com.google.gson.Gson
import com.nytimes.android.external.fs3.FileSystemRecordPersister
import com.nytimes.android.external.fs3.filesystem.FileSystem
import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.Parser
import com.nytimes.android.external.store3.base.Persister
import com.nytimes.android.external.store3.base.impl.Store
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.nytimes.android.external.store3.middleware.GsonParserFactory
import dagger.Module
import dagger.Provides
import okhttp3.ResponseBody
import okio.BufferedSource
import java.util.concurrent.TimeUnit

@Module
class RepositoryListModule {

    @Provides
    internal fun provideSearchRepositoriesStore(
        persister: Persister<BufferedSource, SearchRepositoriesUseCase.Params>,
        parser: Parser<BufferedSource, GHSearchResponse>,
        fetcher: Fetcher<BufferedSource, SearchRepositoriesUseCase.Params>
    ): Store<GHSearchResponse, SearchRepositoriesUseCase.Params> =
        StoreBuilder.parsedWithKey<SearchRepositoriesUseCase.Params, BufferedSource, GHSearchResponse>()
            .fetcher(fetcher)
            .persister(persister)
            .parser(parser)
            .open()

    @Provides
    internal fun provideGHSearchResponseFetcher(
        githubService: GithubService
    ): Fetcher<BufferedSource, SearchRepositoriesUseCase.Params> =
        Fetcher {
            githubService.searchRepositories(it.q, it.page, it.sort, it.ord)
                .map(ResponseBody::source)
        }

    @Provides
    internal fun provideGHSearchResponsePersister(
        fileSystem: FileSystem
    ): Persister<BufferedSource, SearchRepositoriesUseCase.Params> =
        FileSystemRecordPersister.create(
            fileSystem,
            { SearchRepositoriesUseCase.SEARCH_REPOSITORIES_STORE + "_" + it },
            5,
            TimeUnit.HOURS
        )

    @Provides
    internal fun provideGHSearchResponseParser(gson: Gson): Parser<BufferedSource, GHSearchResponse> =
        GsonParserFactory.createSourceParser(gson, GHSearchResponse::class.java)
}