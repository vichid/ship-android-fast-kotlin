package com.example.app.presentation.ui.github.presenters

import com.example.app.domain.exception.DefaultErrorBundle
import com.example.app.domain.interactor.github.RetrieveGithubUserUseCase
import com.example.app.domain.model.github.GithubUserDomain
import com.example.app.presentation.di.scopes.PerActivity
import com.example.app.presentation.ui.base.BasePresenter
import com.example.app.presentation.ui.github.model.Follower
import com.example.app.presentation.ui.github.model.mapper.GithubUserMapper
import com.example.app.presentation.ui.github.views.GithubUserView
import javax.inject.Inject

@PerActivity
class GithubUserPresenter
@Inject
constructor(
        private val retrieveGithubUserUseCase: RetrieveGithubUserUseCase,
        private val githubUserMapper: GithubUserMapper
) : BasePresenter<GithubUserView>() {

    fun loadGithubUser(follower: Follower) {
        subscriptions.add(
                retrieveGithubUserUseCase.init(follower.login).execute()
                        .subscribe(
                                { onGithubUserReceived(it) },
                                { onError(it) },
                                { onRetrieveGithubUserComplete() }
                        )
        )
    }

    fun onGithubUserReceived(githubuserDomain: GithubUserDomain) {
        view?.showGithubUser(githubUserMapper.map(githubuserDomain))
    }

    fun onRetrieveGithubUserComplete() {
        //no-op
    }

    fun onError(throwable: Throwable) {
        view?.onError(DefaultErrorBundle(throwable as Exception).exception)
    }
}

