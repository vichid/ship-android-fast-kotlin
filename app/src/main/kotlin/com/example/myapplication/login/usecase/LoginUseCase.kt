package com.example.myapplication.login.usecase

import com.example.myapplication.AppSchedulers
import com.example.myapplication.base.SingleUseCase
import com.example.myapplication.login.model.LoginResponse
import com.example.myapplication.login.model.User
import com.example.myapplication.util.EmptyParams
import com.example.myapplication.util.Validators
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LoginUseCase
@Inject
constructor(
    appSchedulers: AppSchedulers
) : SingleUseCase<LoginResponse, LoginUseCase.Params>(appSchedulers) {

    override fun validate(params: Params?): Completable = params?.let {
        Completable.concatArray(
            Validators.validateEmail(params.email),
            Validators.validateString(params.password)
        )
    } ?: Completable.error(EmptyParams())

    override fun buildUseCaseObservable(params: Params?, fresh: Boolean):
        Single<LoginResponse> = Single.defer { Single.just(LoginResponse(DUMMY_TOKEN, DUMMY_USER)) }

    data class Params(val email: String, val password: String)

    companion object {
        private val DUMMY_USER: User = User("foo", "foo@example.com")
        private const val DUMMY_TOKEN: String = "f136803ab9c241079ba0cc1b5d02ee77"
    }
}