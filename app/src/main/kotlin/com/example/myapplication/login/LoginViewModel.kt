package com.example.myapplication.login

import android.arch.lifecycle.MutableLiveData
import android.support.annotation.StringRes
import com.example.myapplication.R
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.di.UserManager
import com.example.myapplication.login.usecase.LoginUseCase
import com.example.myapplication.util.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel
@Inject
constructor(
    private val loginUseCase: LoginUseCase,
    private val userManager: UserManager
) : BaseViewModel(), LoginContract.Presenter {

    val loadingStatus = MutableLiveData<Boolean>()
    val loggedInStatus = MutableLiveData<Boolean>()
    val snackbarMessage = SingleLiveEvent<Int>()

    override fun login(email: String, password: String) {
        disposables.add(
            loginUseCase.execute(LoginUseCase.Params(email, password))
                .doOnSubscribe { loadingStatus.value = true }
                .doAfterTerminate { loadingStatus.value = false }
                .map { userManager.createUserSession(it) }
                .subscribe(
                    {
                        loggedInStatus.value = true
                    },
                    {
                        handleError(it)
                    }
                )
        )
    }

    override fun handleError(throwable: Throwable) = when (throwable) {
        is RuntimeException -> showSnackbarMessage(R.string.app_name)
        else -> showSnackbarMessage(R.string.prompt_email)
    }

    private fun showSnackbarMessage(@StringRes message: Int) {
        snackbarMessage.value = message
    }
}