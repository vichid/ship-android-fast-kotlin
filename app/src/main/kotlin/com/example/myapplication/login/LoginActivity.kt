package com.example.myapplication.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.util.ext.isVisible
import com.example.myapplication.util.ext.setupSnackbar
import kotlinx.android.synthetic.main.activity_login.*
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
        observeLoggedInStatus()

        clLogin.setupSnackbar(
            this@LoginActivity,
            loginViewModel.snackbarMessage,
            Snackbar.LENGTH_LONG
        )

        // Set up the login form.
        etPassword.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        btEmailSignInButton.setOnClickListener { attemptLogin() }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin() {

        // Reset errors.
        etEmail.error = null
        etPassword.error = null

        // Store values at the time of the login attempt.
        val emailStr = etEmail.text.toString()
        val passwordStr = etPassword.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            etPassword.error = getString(R.string.error_invalid_password)
            focusView = etPassword
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            etEmail.error = getString(R.string.error_field_required)
            focusView = etEmail
            cancel = true
        } else if (!isEmailValid(emailStr)) {
            etEmail.error = getString(R.string.error_invalid_email)
            focusView = etEmail
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            loginViewModel.login(emailStr, passwordStr)
        }
    }

    private fun isEmailValid(email: String): Boolean = email.contains("@")

    private fun isPasswordValid(password: String): Boolean = password.length > 4

    /**
     * Shows the progress UI and hides the login form.
     */
    private fun showProgress(show: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        svLoginForm.apply {
            isVisible = show
            animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 0 else 1).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        isVisible = show
                    }
                })
        }

        progressBar.apply {
            isVisible = show
            animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        isVisible = show
                    }
                })
        }
    }

    private fun observeLoadingStatus() {
        loginViewModel.loadingStatus.observe(this@LoginActivity,
            Observer<Boolean> { showProgress(it ?: false) }
        )
    }

    private fun observeLoggedInStatus() {
        loginViewModel.loggedInStatus.observe(this@LoginActivity,
            Observer<Boolean> {
                if (it == true) {
                    navigateToHomeActivity()
                }
            }
        )
    }
}
