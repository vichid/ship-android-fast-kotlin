package com.example.app.presentation.ui.github.presenters

import com.example.app.domain.exception.DefaultErrorBundle
import com.example.app.domain.interactor.github.RetrieveUserFollowersByIdUseCase
import com.example.app.domain.model.github.FollowerDomain
import com.example.app.presentation.di.scopes.PerActivity
import com.example.app.presentation.ui.base.BasePresenter
import com.example.app.presentation.ui.github.model.mapper.FollowerMapper
import com.example.app.presentation.ui.github.views.FollowersView
import javax.inject.Inject

@PerActivity
class FollowersPresenter
@Inject
constructor(
        private val retrieveUserFollowersByIdUseCase: RetrieveUserFollowersByIdUseCase,
        private val followerMapper: FollowerMapper
) : BasePresenter<FollowersView>() {

    override fun initialize() {
        super.initialize()
        subscriptions.add(
                retrieveUserFollowersByIdUseCase.init("vichid").execute()
                        .subscribe(
                                { onFollowersReceived(it) },
                                { onError(it) },
                                { onRetrieveFollowersComplete() }
                        )
        )
    }

    fun onFollowersReceived(follwerDomainList: Collection<FollowerDomain>) {
        view?.showFollowers(followerMapper.map(follwerDomainList))
    }

    fun onRetrieveFollowersComplete() {
        //no-op
    }

    fun onError(throwable: Throwable) {
        view?.onError(DefaultErrorBundle(throwable as Exception).exception)
    }
}

