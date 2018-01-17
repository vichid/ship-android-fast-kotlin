package com.example.myapplication.ghrepositories.usecase

import com.example.myapplication.AppSchedulers
import com.example.myapplication.base.SingleUseCase
import com.example.myapplication.ghrepositories.model.GHSearchResponse
import com.example.myapplication.util.EmptyParams
import com.example.myapplication.util.Validators
import com.nytimes.android.external.store3.base.impl.Store
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class SearchRepositoriesUseCase
@Inject
constructor(
    appSchedulers: AppSchedulers,
    @Named("SearchRepositoriesStore")
    private val searchRepositoriesStore: Store<GHSearchResponse, Params>
) : SingleUseCase<GHSearchResponse, SearchRepositoriesUseCase.Params>(appSchedulers) {

    override fun validate(params: Params?): Completable = params?.let {
        Validators.validateString(params.q)
        Validators.validateWholeNumber(params.page)
        Validators.validateString(params.sort)
        Validators.validateString(params.ord)
    } ?: Completable.error(EmptyParams())

    override fun buildUseCaseObservable(params: Params?, fresh: Boolean): Single<GHSearchResponse> =
        if (fresh) {
            searchRepositoriesStore.fetch(params!!)
        } else {
            searchRepositoriesStore.get(params!!)
        }

    data class Params(val q: String, val page: Int, val sort: String, val ord: String)
}