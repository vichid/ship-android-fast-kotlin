package com.example.myapplication.login.usecase

import com.example.myapplication.ExecutionSchedulers
import com.example.myapplication.base.SingleUseCase
import com.example.myapplication.login.model.LoginResponse
import com.example.myapplication.login.model.User
import com.example.myapplication.util.EmailError
import com.example.myapplication.util.EmptyParams
import com.example.myapplication.util.StringError
import com.example.myapplication.util.ValidatorThrowable
import com.example.myapplication.util.validateEmail
import com.example.myapplication.util.validateString
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LoginUseCase
@Inject
constructor(
    executionSchedulers: ExecutionSchedulers
) : SingleUseCase<LoginResponse, LoginUseCase.Params>(executionSchedulers) {

    override fun validate(params: Params?): Completable = params?.let {
        listOfNotNull(
            validateEmail(
                params.email,
                emailEmptyError = EmailEmptyError,
                emailTooShortError = EmailTooShortError,
                emailTooLongError = EmailTooLongError,
                emailPatternNotMatchingError = EmailPatternNotMatchingError
            ),
            validateString(
                params.password,
                max = 100,
                stringTooShortError = PasswordTooShortError,
                stringTooLongError = PasswordTooLongError,
                stringEmptyError = PasswordEmptyError
            )
        ).let {
            if (it.isEmpty()) {
                Completable.complete()
            } else {
                Completable.error(ValidatorThrowable(it))
            }
        }
    } ?: Completable.error(ValidatorThrowable(listOf(EmptyParams)))

    override fun buildUseCase(params: Params?, fresh: Boolean): Single<LoginResponse> =
        Single.defer { Single.just(LoginResponse(DUMMY_TOKEN, DUMMY_USER)) }

    /**
     * UseCase parameters
     */
    data class Params(val email: String, val password: String)

    object EmailEmptyError : EmailError()
    object EmailTooShortError : EmailError()
    object EmailTooLongError : EmailError()
    object EmailPatternNotMatchingError : EmailError()

    object PasswordTooShortError : StringError()
    object PasswordTooLongError : StringError()
    object PasswordEmptyError : StringError()

    companion object {
        val DUMMY_USER: User = User("john.doe", "john.doe@gmail.com")
        const val DUMMY_TOKEN: String = "f136803ab9c241079ba0cc1b5d02ee77"
    }
}