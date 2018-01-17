package com.example.myapplication.ghrepositories.usecase

import com.example.myapplication.AppSchedulers
import com.example.myapplication.base.SingleUseCase
import com.example.myapplication.ghrepositories.model.GHRepository
import com.example.myapplication.util.EmptyParams
import com.example.myapplication.util.Validators
import com.nytimes.android.external.store3.base.impl.Store
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class RetrieveUserRepositoriesUseCase
@Inject
constructor(
    appSchedulers: AppSchedulers,
    @Named(RETRIEVE_USER_REPOSITORIES_STORE)
    private val retrieveUserRepositoriesStore: Store<List<GHRepository>, String>
) : SingleUseCase<List<GHRepository>, RetrieveUserRepositoriesUseCase.Params>(appSchedulers) {

    override fun validate(params: Params?): Completable = params?.let {
        Validators.validateString(params.login)
    } ?: Completable.error(EmptyParams())

    override fun buildUseCaseObservable(params: Params?, fresh: Boolean): Single<List<GHRepository>> =
        if (fresh) {
            retrieveUserRepositoriesStore.fetch(params!!.login)
        } else {
            retrieveUserRepositoriesStore.get(params!!.login)
        }

    data class Params(val login: String)

    companion object {
        const val RETRIEVE_USER_REPOSITORIES_STORE: String = "RETRIEVE_USER_REPOSITORIES_STORE"
    }
}