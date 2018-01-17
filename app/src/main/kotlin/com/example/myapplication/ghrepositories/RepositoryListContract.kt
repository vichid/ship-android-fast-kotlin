package com.example.myapplication.ghrepositories

import com.example.myapplication.base.LoadingView
import com.example.myapplication.base.RetryView

interface RepositoryListContract {
    interface Presenter {
        fun searchRepositories()
        fun handleError(throwable: Throwable)
    }

    interface View : LoadingView, RetryView
}