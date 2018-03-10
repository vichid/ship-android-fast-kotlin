package com.example.myapplication.ghrepositories.usecase

import com.example.myapplication.ghrepositories.model.GHSearchResponse
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase.NegativeNumberError
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase.OrderEmptyError
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase.OrderTooLongError
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase.OrderTooShortError
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase.Params
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase.QueryEmptyError
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase.QueryTooLongError
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase.QueryTooShortError
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase.SortEmptyError
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase.SortTooLongError
import com.example.myapplication.ghrepositories.usecase.SearchRepositoriesUseCase.SortTooShortError
import com.example.myapplication.testutils.TestAppSchedulers
import com.example.myapplication.util.EmptyParams
import com.example.myapplication.util.ValidatorThrowable
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.nytimes.android.external.store3.base.impl.Store
import io.reactivex.Single
import org.amshove.kluent.mock
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object SearchRepositoriesUseCaseTest : Spek({
    given("a usecase that search for repositories") {

        val testSchedulers = TestAppSchedulers()
        val searchRepositoriesStore = mock<Store<GHSearchResponse, SearchRepositoriesUseCase.Params>>()
        val searchRepositoriesUseCase = SearchRepositoriesUseCase(testSchedulers, searchRepositoriesStore)
        val ghSearchResponse = GHSearchResponse()

        on("execution without parameters") {
            searchRepositoriesUseCase.execute()
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(EmptyParams).toString())
                        verifyZeroInteractions(searchRepositoriesStore)
                    }
                }
        }

        on("execution with empty query parameter") {
            searchRepositoriesUseCase.execute(Params("", 1, "stars", "desc"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(QueryEmptyError).toString())
                        verifyZeroInteractions(searchRepositoriesStore)
                    }
                }
        }

        on("execution with negative page parameter") {
            searchRepositoriesUseCase.execute(Params("android", -1, "stars", "desc"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(NegativeNumberError).toString())
                        verifyZeroInteractions(searchRepositoriesStore)
                    }
                }
        }

        on("execution with empty sort parameter") {
            searchRepositoriesUseCase.execute(Params("android", 1, "", "desc"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(SortEmptyError).toString())
                        verifyZeroInteractions(searchRepositoriesStore)
                    }
                }
        }

        on("execution with empty order parameter") {
            searchRepositoriesUseCase.execute(Params("android", 1, "stars", ""))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(OrderEmptyError).toString())
                        verifyZeroInteractions(searchRepositoriesStore)
                    }
                }
        }

        on("execution with short query parameter") {
            searchRepositoriesUseCase.execute(Params("as", 1, "stars", "desc"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(QueryTooShortError).toString())
                        verifyZeroInteractions(searchRepositoriesStore)
                    }
                }
        }

        on("execution with short sort parameter") {
            searchRepositoriesUseCase.execute(Params("android", 1, "st", "desc"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(SortTooShortError).toString())
                        verifyZeroInteractions(searchRepositoriesStore)
                    }
                }
        }

        on("execution with short order parameter") {
            searchRepositoriesUseCase.execute(Params("android", 1, "stars", "de"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(OrderTooShortError).toString())
                        verifyZeroInteractions(searchRepositoriesStore)
                    }
                }
        }

        on("execution with long query parameter") {
            searchRepositoriesUseCase.execute(Params(randomAlphanumeric(40), 1, "stars", "desc"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(QueryTooLongError).toString())
                        verifyZeroInteractions(searchRepositoriesStore)
                    }
                }
        }
        on("execution with long sort parameter") {
            searchRepositoriesUseCase.execute(Params("android", 1, randomAlphanumeric(40), "desc"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(SortTooLongError).toString())
                        verifyZeroInteractions(searchRepositoriesStore)
                    }
                }
        }

        on("execution with long order parameter") {
            searchRepositoriesUseCase.execute(Params("android", 1, "stars", randomAlphanumeric(40)))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(OrderTooLongError).toString())
                        verifyZeroInteractions(searchRepositoriesStore)
                    }
                }
        }

        on("proper fresh execution") {
            val params = Params("android", 1, "stars", "desc")
            whenever(searchRepositoriesStore.fetch(params)).thenReturn(Single.just(ghSearchResponse))
            searchRepositoriesUseCase.execute(params, true)
                .test()
                .let { to ->
                    it("should call a fresh store and return a " + GHSearchResponse::class.java.simpleName) {
                        to.assertResult(ghSearchResponse)
                        verify(searchRepositoriesStore, times(1)).fetch(params)
                        verifyNoMoreInteractions(searchRepositoriesStore)
                    }
                }
        }

        on("proper get execution") {
            val params = Params("android", 1, "stars", "desc")
            whenever(searchRepositoriesStore.get(params)).thenReturn(Single.just(ghSearchResponse))
            searchRepositoriesUseCase.execute(params)
                .test()
                .let { to ->
                    it("should call the store and return a " + GHSearchResponse::class.java.simpleName) {
                        to.assertResult(ghSearchResponse)
                        verify(searchRepositoriesStore, times(1)).get(params)
                        verifyNoMoreInteractions(searchRepositoriesStore)
                    }
                }
        }
    }
})