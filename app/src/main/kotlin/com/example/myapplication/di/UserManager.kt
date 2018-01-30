package com.example.myapplication.di

import android.app.Activity
import android.content.Context
import com.example.myapplication.login.model.LoginResponse
import com.example.myapplication.login.model.User
import com.example.myapplication.util.bindSharedPreference
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager
@Inject
constructor(
    private val userComponentBuilder: UserComponent.Builder,
    context: Context
) : HasActivityInjector {

    private var userName: String by bindSharedPreference(context, USER_NAME, "")
    private var userEmail: String by bindSharedPreference(context, USER_EMAIL, "")
    private var userToken: String by bindSharedPreference(context, USER_TOKEN, "")

    private var userComponent: UserComponent? = null

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    private fun buildUserComponent(loginResponse: LoginResponse): UserComponent =
        userComponentBuilder
            .user(loginResponse.user)
            .token(loginResponse.sessionToken)
            .build()

    fun injectIfNecessary() {
        if (userName.isNotBlank() && userEmail.isNotBlank() && userToken.isNotBlank()) {
            buildUserComponent(LoginResponse(userToken, User(userName, userEmail)))
                .apply { inject(this@UserManager) }
                .let { userComponent = it }
        }
    }

    fun createUserSession(loginResponse: LoginResponse): Completable =
        buildUserComponent(loginResponse)
            .apply { inject(this@UserManager) }
            .let {
                userName = loginResponse.user.name
                userEmail = loginResponse.user.email
                userToken = loginResponse.sessionToken
                userComponent = it
                Completable.complete()
            }

    val isLoggedIn: Boolean
        get() = userComponent != null

    fun logOut() {
        userComponent = null
        userName = ""
        userEmail = ""
        userToken = ""
    }

    companion object {
        private const val USER_NAME: String = "USER_NAME"
        private const val USER_EMAIL: String = "USER_EMAIL"
        private const val USER_TOKEN: String = "USER_TOKEN"
    }
}
