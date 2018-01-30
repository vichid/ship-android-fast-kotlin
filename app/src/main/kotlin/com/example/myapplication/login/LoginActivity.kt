package com.example.myapplication.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.util.Status
import com.example.myapplication.util.ext.isVisible
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.view_progress.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProviders
            .of(this@LoginActivity, viewModelFactory)
            .get(LoginViewModel::class.java)

        observeLoadingStatus()

        etPassword.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            return@OnEditorActionListener if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                performLogin()
                true
            } else false
        })

        btEmailSignInButton.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        loginViewModel.email.value = etEmail.text.toString()
        loginViewModel.password.value = etPassword.text.toString()
        loginViewModel.login()
    }

    override fun showLoading(state: Boolean) {
        progressBar.apply {
            isVisible = state
            animate()
                .setDuration(resources.getInteger(android.R.integer.config_shortAnimTime).toLong())
                .alpha((if (state) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        isVisible = state
                    }
                })
        }
    }

    private fun observeLoadingStatus() {
        loginViewModel.status.observe(this@LoginActivity,
            Observer<Status> {
                when (it) {
                    Status.Success -> {
                        navigateToHomeActivity()
                    }
                    Status.Loading -> {
                        showLoading(true)
                    }
                    Status.Error -> {
                        showLoading(false)
                        //TODO: Show errors
                    }
                }
            }
        )
    }
}
