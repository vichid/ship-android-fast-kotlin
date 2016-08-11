package com.example.app.presentation.ui.featureName.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.app.domain.model.Github
import com.example.app.presentation.ui.base.BaseActivity
import com.example.app.presentation.ui.featureName.ankocomponents.MainActivityUI
import com.example.app.presentation.ui.featureName.presenters.MainPresenter
import com.example.app.presentation.ui.featureName.views.MainView
import org.jetbrains.anko.setContentView
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var mainPresenter: MainPresenter

    lateinit var userImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        getActivityComponent().inject(this)
        mainPresenter.view = this;
        mainPresenter.onCreate()
    }

    override fun renderView(github: Github) {
        Glide.with(this).load(github?.avatarUrl).into(userImage)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, "Something was wrong", Toast.LENGTH_LONG).show()
    }
}
