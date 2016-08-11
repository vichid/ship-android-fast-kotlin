package com.example.app.presentation.ui.featureName.presenters

import com.example.app.domain.model.Github
import com.example.app.domain.usingcases.GithubUseCase
import com.example.app.presentation.di.scopes.PerActivity
import com.example.app.presentation.ui.featureName.views.MainView
import rx.lang.kotlin.FunctionSubscriber
import javax.inject.Inject

@PerActivity
class MainPresenter @Inject
constructor(private val githubUseCase: GithubUseCase) {

    var view: MainView? = null

    fun onCreate() {
        githubUseCase.setId("vichid")
        githubUseCase.execute(FunctionSubscriber<Github>()
                     .onNext { view?.renderView(it) }
                     .onError { view?.showError(it) })
    }
}

