package com.example.myapplication.ghrepositories.usecase

import com.example.myapplication.TestAppSchedulers
import com.example.myapplication.ghrepositories.model.GHSearchResponse
import com.example.myapplication.util.EmptyParams
import com.nytimes.android.external.store3.base.impl.Store
import org.amshove.kluent.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object SearchRepositoriesUseCaseTest : Spek({
    given("a usecase that search for repositories") {

        val testSchedulers = TestAppSchedulers()
        val searchRepositoriesStore: Store<GHSearchResponse, SearchRepositoriesUseCase.Params> = mock()
        val searchRepositoriesUseCase = SearchRepositoriesUseCase(testSchedulers, searchRepositoriesStore)

        on("execution without parameters") {
            it("should throw error") {
                searchRepositoriesUseCase.execute()
                    .test()
                    .assertFailure(EmptyParams::class.java)
            }
        }
    }
})