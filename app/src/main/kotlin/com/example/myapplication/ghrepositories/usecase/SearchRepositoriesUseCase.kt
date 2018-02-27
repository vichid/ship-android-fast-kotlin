package com.example.myapplication.ghrepositories.usecase

import com.example.myapplication.ExecutionSchedulers
import com.example.myapplication.base.SingleUseCase
import com.example.myapplication.ghrepositories.model.GHSearchResponse
import com.example.myapplication.util.EmptyParams
import com.example.myapplication.util.NumberError
import com.example.myapplication.util.StringError
import com.example.myapplication.util.ValidatorThrowable
import com.example.myapplication.util.validateString
import com.example.myapplication.util.validateWholeNumber
import com.nytimes.android.external.store3.base.impl.Store
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoriesUseCase
@Inject
constructor(
    executionSchedulers: ExecutionSchedulers,
    private val searchRepositoriesStore: Store<GHSearchResponse, Params>
) : SingleUseCase<GHSearchResponse, SearchRepositoriesUseCase.Params>(executionSchedulers) {

    override fun validate(params: Params?): Completable = params?.let {
        listOfNotNull(
            validateString(
                params.q,
                stringTooShortError = QueryTooShortError,
                stringTooLongError = QueryTooLongError,
                stringEmptyError = QueryEmptyError
            ),
            validateWholeNumber(params.page, negativeNumberError = NegativeNumberError),
            validateString(
                params.sort,
                stringTooShortError = SortTooShortError,
                stringTooLongError = SortTooLongError,
                stringEmptyError = SortEmptyError
            ),
            validateString(
                params.ord,
                stringTooShortError = OrderTooShortError,
                stringTooLongError = OrderTooLongError,
                stringEmptyError = OrderEmptyError
            )
        ).let {
            if (it.isEmpty()) {
                Completable.complete()
            } else {
                Completable.error(ValidatorThrowable(it))
            }
        }
    } ?: Completable.error(ValidatorThrowable(listOf(EmptyParams)))

    override fun buildUseCase(params: Params?, fresh: Boolean): Single<GHSearchResponse> =
        Single.defer {
            if (fresh) {
                searchRepositoriesStore.fetch(params!!)
            } else {
                searchRepositoriesStore.get(params!!)
            }
        }

    data class Params(val q: String, val page: Int, val sort: String, val ord: String)

    object QueryTooShortError : StringError()
    object QueryTooLongError : StringError()
    object QueryEmptyError : StringError()
    object SortTooShortError : StringError()
    object SortTooLongError : StringError()
    object SortEmptyError : StringError()
    object OrderTooShortError : StringError()
    object OrderTooLongError : StringError()
    object OrderEmptyError : StringError()
    object NegativeNumberError : NumberError()

    companion object {
        const val SEARCH_REPOSITORIES_STORE: String = "SEARCH_REPOSITORIES_STORE"
    }
}