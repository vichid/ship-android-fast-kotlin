package com.example.app.presentation.ui.github.presenters

import com.example.app.domain.exception.DefaultErrorBundle
import com.example.app.domain.interactor.github.RetrieveGithubUserUseCase
import com.example.app.domain.model.github.GithubUserDomain
import com.example.app.presentation.di.scopes.PerActivity
import com.example.app.presentation.ui.github.model.Follower
import com.example.app.presentation.ui.github.model.mapper.GithubUserMapper
import com.example.app.presentation.ui.github.views.GithubUserView
import rx.lang.kotlin.FunctionSubscriber
import timber.log.Timber
import javax.inject.Inject

@PerActivity
class GithubUserPresenter
@Inject
constructor(
        private val retrieveGithubUserUseCase: RetrieveGithubUserUseCase,
        private val githubUserMapper: GithubUserMapper
) {

    var view: GithubUserView? = null

    fun init(follower: Follower) {
        retrieveGithubUserUseCase
                .init(follower.login)
                .execute(
                        FunctionSubscriber<GithubUserDomain>()
                                .onStart { Timber.d("executing") }
                                .onNext { view?.renderView(githubUserMapper.map(it)) }
                                .onCompleted { Timber.d("completed") }
                                .onError {
                                    view?.showError(DefaultErrorBundle(it as Exception).exception)
                                }
                )
    }
}

