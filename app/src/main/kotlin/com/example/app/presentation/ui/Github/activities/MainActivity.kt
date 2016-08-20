package com.example.app.presentation.ui.github.activities

import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.app.presentation.ui.base.BaseActivity
import com.example.app.presentation.ui.github.ankocomponents.MainActivityUI
import com.example.app.presentation.ui.github.model.GithubUser
import com.example.app.presentation.ui.github.presenters.MainPresenter
import com.example.app.presentation.ui.github.views.MainView
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var mainPresenter: MainPresenter

    lateinit var userImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        getActivityComponent().inject(this)
        mainPresenter.view = this;
        mainPresenter.onCreate()
    }

    override fun renderView(githubUser: GithubUser) {
        Glide.with(this).load(githubUser.avatarUrl).into(userImage)
    }

    override fun showError(throwable: Throwable) {
        toast("Something was wrong")
    }
}
