package com.example.app.presentation.ui.github.presenters

import com.example.app.domain.exception.DefaultErrorBundle
import com.example.app.domain.interactor.github.RetrieveFollowersUseCase
import com.example.app.domain.model.github.FollowerDomain
import com.example.app.presentation.di.scopes.PerActivity
import com.example.app.presentation.ui.github.model.mapper.FollowerMapper
import com.example.app.presentation.ui.github.views.FollowersView
import rx.lang.kotlin.FunctionSubscriber
import timber.log.Timber
import javax.inject.Inject

@PerActivity
class FollowersPresenter
@Inject
constructor(
        private val retrieveFollowersUseCase: RetrieveFollowersUseCase,
        private val followerMapper: FollowerMapper
) {

    var view: FollowersView? = null

    fun init() {
        retrieveFollowersUseCase
                .init("vichid")
                .execute(
                        FunctionSubscriber<List<FollowerDomain>>()
                                .onStart { Timber.d("executing") }
                                .onNext { view?.showFollowers(followerMapper.transform(it)) }
                                .onCompleted { Timber.d("completed") }
                                .onError {
                                    view?.showError(DefaultErrorBundle(it as Exception).exception)
                                }
                )
    }
}

