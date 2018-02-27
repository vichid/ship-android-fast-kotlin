package com.example.myapplication.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.util.ext.showShortSnackbar
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun createViewModel() {
        viewModel = ViewModelProviders
            .of(this@LoginActivity, viewModelFactory)
            .get(LoginViewModel::class.java)
    }

    override fun createBinding() {
        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        binding.apply {
            viewmodel = viewModel
            setLifecycleOwner(this@LoginActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            emailError.observe(this@LoginActivity, Observer {
                tilEmail.error = it?.let { getText(it) }
            })
            passwordError.observe(this@LoginActivity, Observer {
                tilPassword.error = it?.let { getText(it) }
            })
            snackbarMessage.observe(this@LoginActivity, Observer {
                it?.let { clLogin.showShortSnackbar(getString(it)) }
            })
            isLoginCompleted.observe(this@LoginActivity, Observer {
                if (it == true) navigateToHomeActivity()
            })
        }
    }
}
