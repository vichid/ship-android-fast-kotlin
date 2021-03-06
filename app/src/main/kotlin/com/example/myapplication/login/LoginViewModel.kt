package com.example.myapplication.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.di.UserManager
import com.example.myapplication.login.usecase.LoginUseCase
import com.example.myapplication.util.EmptyParams
import com.example.myapplication.util.SingleLiveEvent
import com.example.myapplication.util.ValidatorThrowable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class LoginViewModel
@Inject
constructor(
    private val loginUseCase: LoginUseCase,
    private val userManager: UserManager
) : BaseViewModel() {

    private val isLoading = MutableLiveData<Boolean>()
    private val isLoginCompleted = MutableLiveData<Boolean>()
    private val emailError = MutableLiveData<Int>()
    private val passwordError = MutableLiveData<Int>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private val snackbarMessage = SingleLiveEvent<Int>()

    init {
        email.value = "john.doe@gmail.com"
        password.value = "123456"
    }

    fun getIsLoading(): LiveData<Boolean> = isLoading
    fun getIsLoadingCompleted(): LiveData<Boolean> = isLoginCompleted
    fun getEmailError(): LiveData<Int> = emailError
    fun getPasswordError(): LiveData<Int> = passwordError
    fun getSnackbarMessage(): LiveData<Int> = snackbarMessage

    fun login() {
        emailError.value = null
        passwordError.value = null

        val paramsSnapshot = LoginUseCase.Params(
            email.value.orEmpty(),
            password.value.orEmpty()
        )
        loginUseCase.execute(paramsSnapshot)
            .doOnSubscribe { isLoading.value = true }
            .doAfterTerminate { isLoading.value = false }
            .map { userManager.createUserSession(it) }
            .subscribe(
                { isLoginCompleted.value = true },
                { handleError(it) }
            )
            .addTo(disposables)
    }

    private fun handleError(t: Throwable) = when (t) {
        is ValidatorThrowable -> {
            t.list.forEach {
                when (it) {
                    EmptyParams -> {
                        emailError.value = R.string.error_field_required
                        passwordError.value = R.string.error_field_required
                    }
                    LoginUseCase.EmailTooShortError -> {
                        emailError.value = R.string.error_invalid_email_short
                    }
                    LoginUseCase.EmailPatternNotMatchingError -> {
                        emailError.value = R.string.error_invalid_email
                    }
                    LoginUseCase.PasswordEmptyError -> {
                        passwordError.value = R.string.error_field_required
                    }
                    LoginUseCase.PasswordTooShortError -> {
                        passwordError.value = R.string.error_invalid_password_short
                    }
                    else -> {
                        snackbarMessage.setValue(R.string.error_dummy)
                    }
                }
            }
        }
        else -> {
            snackbarMessage.setValue(R.string.error_dummy)
        }
    }
}