package com.example.myapplication.login.usecase

import com.example.myapplication.TestAppSchedulers
import com.example.myapplication.login.model.LoginResponse
import com.example.myapplication.login.usecase.LoginUseCase.EmailEmptyError
import com.example.myapplication.login.usecase.LoginUseCase.EmailPatternNotMatchingError
import com.example.myapplication.login.usecase.LoginUseCase.EmailTooLongError
import com.example.myapplication.login.usecase.LoginUseCase.EmailTooShortError
import com.example.myapplication.login.usecase.LoginUseCase.Params
import com.example.myapplication.login.usecase.LoginUseCase.PasswordEmptyError
import com.example.myapplication.login.usecase.LoginUseCase.PasswordTooLongError
import com.example.myapplication.login.usecase.LoginUseCase.PasswordTooShortError
import com.example.myapplication.util.EmptyParams
import com.example.myapplication.util.ValidatorThrowable
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object LoginUseCaseTest : Spek({
    given("a usecase that tries to login") {

        val testSchedulers = TestAppSchedulers()
        val loginUseCase = LoginUseCase(testSchedulers)
        val loginResponse = LoginResponse(LoginUseCase.DUMMY_TOKEN, LoginUseCase.DUMMY_USER)

        on("execution without parameters") {
            loginUseCase.execute()
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(EmptyParams).toString())
                    }
                }
        }

        on("execution with wrong email parameter") {
            loginUseCase.execute(Params("john.doe", "123456"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(EmailPatternNotMatchingError).toString())
                    }
                }
        }

        on("execution with empty email parameter") {
            loginUseCase.execute(Params("", "123456"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(EmailEmptyError).toString())
                    }
                }
        }

        on("execution with empty password parameter") {
            loginUseCase.execute(Params("john.doe@gmail.com", ""))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(PasswordEmptyError).toString())
                    }
                }
        }

        on("execution with short email parameter") {
            loginUseCase.execute(Params("j@g.c", "123456"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(EmailTooShortError).toString())
                    }
                }
        }

        on("execution with short password parameter") {
            loginUseCase.execute(Params("john.doe@gmail.com", "12"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(PasswordTooShortError).toString())
                    }
                }
        }

        on("execution with long email parameter") {
            loginUseCase.execute(Params(randomAlphanumeric(60) + "@test.com", "123456"))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(EmailTooLongError).toString())
                    }
                }
        }

        on("execution with long password parameter") {
            loginUseCase.execute(Params("john.doe@gmail.com", randomAlphanumeric(101)))
                .test()
                .let { to ->
                    it("should throw " + ValidatorThrowable::class.java.simpleName) {
                        to.assertFailureAndMessage(ValidatorThrowable::class.java, listOf(PasswordTooLongError).toString())
                    }
                }
        }

        on("proper execution") {
            val params = Params("john.doe@gmail.com", "123456")
            loginUseCase.execute(params)
                .test()
                .let { to ->
                    it("should return a " + LoginResponse::class.java.simpleName) {
                        to.assertResult(loginResponse)
                    }
                }
        }
    }
})