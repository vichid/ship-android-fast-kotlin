package com.example.app.presentation.ui.base

import android.support.v7.app.AppCompatActivity
import com.example.app.presentation.AndroidApplication
import com.example.app.presentation.di.components.ActivityComponent
import com.example.app.presentation.di.modules.ActivityModule

/**
 * Base [android.app.Activity] class for every Activity in this application.
 */
abstract class BaseActivity : AppCompatActivity() {

    val activityComponent: ActivityComponent by lazy {
        (application as AndroidApplication).component.plus(ActivityModule(this))
    }
}
