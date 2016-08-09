package com.example.app.presentation.ui.main.presenters

import com.example.app.domain.model.Github
import com.example.app.domain.usingcases.GithubUseCase
import com.example.app.presentation.internal.di.scope.PerActivity
import com.example.app.presentation.ui.main.views.MainView
import rx.lang.kotlin.FunctionSubscriber
import javax.inject.Inject

@PerActivity
class MainPresenter @Inject
constructor(private val githubUseCase: GithubUseCase) {

    var view: MainView? = null

    fun onCreate() {
        githubUseCase.setId("djuarez")
        githubUseCase.execute(FunctionSubscriber<Github>()
                     .onNext { view?.renderView(it) }
                     .onError { view?.showError(it) })
    }
}

