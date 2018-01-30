package com.example.myapplication.login

import android.arch.lifecycle.MutableLiveData
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.di.UserManager
import com.example.myapplication.login.usecase.LoginUseCase
import com.example.myapplication.util.Status
import javax.inject.Inject

class LoginViewModel
@Inject
constructor(
    private val loginUseCase: LoginUseCase,
    private val userManager: UserManager
) : BaseViewModel(), LoginContract.ViewModel {

    val status = MutableLiveData<Status>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    override fun login() {
        val paramsSnapshot = LoginUseCase.Params(
            email.value.orEmpty(),
            password.value.orEmpty()
        )
        disposables.add(
            loginUseCase.execute(paramsSnapshot)
                .doOnSubscribe {
                    status.value = Status.Loading
                }
                .map { userManager.createUserSession(it) }
                .subscribe(
                    {
                        status.value = Status.Success
                    },
                    {
                        status.value = Status.Error
                        handleError(it)
                    }
                )
        )
    }

    override fun handleError(throwable: Throwable) = when (throwable) {
        is RuntimeException -> {}
        else -> {}
    }
}