package com.example.myapplication.base

import android.os.Bundle
import com.example.myapplication.di.UserManager
import com.example.myapplication.launch.LaunchActivity
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import javax.inject.Inject

abstract class BaseUserActivity : BaseActivity() {

    @Inject
    lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!userManager.isLoggedIn) {
            finishView()
        }
    }

    protected fun logoutUser() {
        userManager.logOut()
        finishView()
    }

    private fun finishView() {
        startActivity(intentFor<LaunchActivity>().clearTask())
    }
}