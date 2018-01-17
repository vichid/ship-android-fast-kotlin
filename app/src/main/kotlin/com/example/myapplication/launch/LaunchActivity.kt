package com.example.myapplication.launch

import android.app.Activity
import android.os.Bundle
import com.example.myapplication.App

class LaunchActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when ((application as App).userManager.isLoggedIn) {
            true -> navigateToHomeActivity()
            false -> navigateToLoginActivity()
        }
        finish()
    }
}
