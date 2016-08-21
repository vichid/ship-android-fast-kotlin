package com.example.app.presentation.ui.github.presenters

import com.example.app.domain.interactor.github.GetGithubUserUseCase
import com.example.app.domain.model.github.GithubUserDomain
import com.example.app.presentation.di.scopes.PerActivity
import com.example.app.presentation.ui.github.model.mapper.GithubUserMapper
import com.example.app.presentation.ui.github.views.GithubUserView
import rx.lang.kotlin.FunctionSubscriber
import javax.inject.Inject

@PerActivity
class GithubUserPresenter
@Inject
constructor(
        private val getGithubUserUseCase: GetGithubUserUseCase,
        private val githubUserMapper: GithubUserMapper
) {

    var view: GithubUserView? = null

    fun onCreate() {
        getGithubUserUseCase
                .init("vichid")
                .execute(
                        FunctionSubscriber<GithubUserDomain>()
                                .onNext { view?.renderView(githubUserMapper.transformToFrom(it)) }
                                .onError { view?.showError(it) }
                )
    }
}

