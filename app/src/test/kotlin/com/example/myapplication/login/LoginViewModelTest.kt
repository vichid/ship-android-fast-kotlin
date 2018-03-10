package com.example.myapplication.login

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.example.myapplication.di.UserManager
import com.example.myapplication.login.model.LoginResponse
import com.example.myapplication.login.model.User
import com.example.myapplication.login.usecase.LoginUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Single
import org.amshove.kluent.mock
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoginViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var loginViewModel: LoginViewModel

    lateinit var loginUseCase: LoginUseCase
    lateinit var userManager: UserManager

    @Before
    fun setUp() {
        loginUseCase = mock()
        userManager = mock()
        loginViewModel = LoginViewModel(loginUseCase, userManager)
    }

    @Test
    fun shouldNotReturnAnythingOnLogin() {
        whenever(loginUseCase.execute(any(), any())).thenReturn(Single.never())

        loginViewModel.login()

        loginViewModel.getEmailError().value shouldEqual null
        loginViewModel.getPasswordError().value shouldEqual null

        verify(loginUseCase, times(1)).execute(any(), any())
        verifyNoMoreInteractions(loginUseCase)
        verifyZeroInteractions(userManager)
    }

    @Test
    fun shouldSuccessOnLogin() {
        val loginResponse = givenALoginResponse()
        whenever(loginUseCase.execute(any(), any())).thenReturn(Single.just(loginResponse))
        whenever((userManager.createUserSession(any()))).thenReturn(Completable.complete())
        val observer: Observer<Boolean> = mock()
        val captor = argumentCaptor<Boolean>()
        loginViewModel.getIsLoading().observeForever(observer)

        loginViewModel.login()

        loginViewModel.getEmailError().value shouldEqual null
        loginViewModel.getPasswordError().value shouldEqual null
        loginViewModel.getIsLoadingCompleted().value shouldEqual true
        verify(observer, times(2)).onChanged(captor.capture())
        captor.firstValue shouldEqual true
        captor.secondValue shouldEqual false
        verify(loginUseCase, times(1)).execute(any(), any())
        verifyNoMoreInteractions(loginUseCase)
        verify(userManager, times(1)).createUserSession(loginResponse)
        verifyNoMoreInteractions(userManager)
    }

    private fun givenALoginResponse(): LoginResponse =
        LoginResponse("f136803ab9c241079ba0cc1b5d02ee77", User("john.doe", "john.doe@gmail.com"))
}
