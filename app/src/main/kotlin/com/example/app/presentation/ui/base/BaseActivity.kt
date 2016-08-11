package com.example.app.presentation.ui.base

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.app.presentation.di.components.ActivityComponent
import com.example.app.presentation.di.factories.ActivityComponentFactory

/**
 * Base [android.app.Activity] class for every Activity in this application.
 */
abstract class BaseActivity : AppCompatActivity() {

    private var activityComponent: ActivityComponent? = null

    fun getActivityComponent(): ActivityComponent {
        if (activityComponent == null) {
            activityComponent = ActivityComponentFactory.create(this)
        }
        return activityComponent as ActivityComponent
    }

    /**
     * Adds a [Fragment] to this activity's layout.
     * @param containerViewId The container view to where add the fragment.
     * *
     * @param fragment The fragment to be added.
     */
    internal fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment, fragment.javaClass.name)
        fragmentTransaction.commit()
    }
}
