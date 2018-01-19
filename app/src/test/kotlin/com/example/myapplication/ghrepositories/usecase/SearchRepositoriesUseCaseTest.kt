package com.example.myapplication.ghrepositories.usecase

import com.example.myapplication.TestAppSchedulers
import com.example.myapplication.ghrepositories.model.GHSearchResponse
import com.example.myapplication.util.EmptyParams
import com.example.myapplication.util.StringEmptyError
import com.example.myapplication.util.StringTooLongError
import com.example.myapplication.util.StringTooShortError
import com.nhaarman.mockito_kotlin.whenever
import com.nytimes.android.external.store3.base.impl.Store
import io.reactivex.Single
import org.amshove.kluent.mock
import org.apache.commons.lang3.RandomStringUtils
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object SearchRepositoriesUseCaseTest : Spek({
    given("a usecase that search for repositories") {

        val testSchedulers = TestAppSchedulers()
        val searchRepositoriesStore = mock<Store<GHSearchResponse, SearchRepositoriesUseCase.Params>>()
        val searchRepositoriesUseCase = SearchRepositoriesUseCase(testSchedulers, searchRepositoriesStore)

        on("execution without parameters") {
            it("should throw " + EmptyParams::class.java.simpleName) {
                searchRepositoriesUseCase.execute()
                    .test()
                    .assertFailure(EmptyParams::class.java)
            }
        }

        on("execution with empty query parameter") {
            it("should throw " + StringEmptyError::class.java.simpleName) {
                searchRepositoriesUseCase.execute(SearchRepositoriesUseCase.Params("", 1, "stars", "desc"))
                    .test()
                    .assertFailure(StringEmptyError::class.java)
            }
        }

        on("execution with short query parameter") {
            it("should throw " + StringTooShortError::class.java.simpleName) {
                searchRepositoriesUseCase.execute(SearchRepositoriesUseCase.Params("as", 1, "stars", "desc"))
                    .test()
                    .assertFailure(StringTooShortError::class.java)
            }
        }

        on("execution with long query parameter") {
            it("should throw " + StringTooLongError::class.java.simpleName) {
                searchRepositoriesUseCase.execute(SearchRepositoriesUseCase.Params(
                    RandomStringUtils.randomAlphanumeric(40), 1, "stars", "desc")
                )
                    .test()
                    .assertFailure(StringTooLongError::class.java)
            }
        }

        on("proper fresh execution") {
            val params = SearchRepositoriesUseCase.Params("android", 1, "stars", "desc")
            val response = mock<GHSearchResponse>()
            whenever(searchRepositoriesStore.fetch(params)).thenReturn(Single.just(response))
            it("should call a fresh store and return a " + GHSearchResponse::class.java.simpleName) {
                searchRepositoriesUseCase.execute(params, true)
                    .test()
                    .assertResult(response)
            }
        }

        on("proper get execution") {
            val params = SearchRepositoriesUseCase.Params("android", 1, "stars", "desc")
            val response = mock<GHSearchResponse>()
            whenever(searchRepositoriesStore.get(params)).thenReturn(Single.just(response))
            it("should call the store and return a " + GHSearchResponse::class.java.simpleName) {
                searchRepositoriesUseCase.execute(params)
                    .test()
                    .assertResult(response)
            }
        }
    }
})