package com.example.myapplication.util.ext

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.app_bar_main.*

fun FragmentActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager?.let { sfm ->
        if ((sfm.findFragmentById(flContainer.id))?.isAdded != true) {
            sfm.transact {
                replace(frameId, fragment)
            }
        }
    }
}

fun FragmentActivity.addSubFragment(fragment: Fragment, frameId: Int, tag: String? = null) {
    supportFragmentManager?.let { sfm ->
        if ((sfm.findFragmentById(flContainer.id))?.isAdded != true) {
            sfm.transact {
                addToBackStack(tag)
                replace(frameId, fragment)
            }
        }
    }
}

/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun AppCompatActivity.setupActionBar(toolbar: Toolbar, action: ActionBar.() -> Unit = {}) {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        action()
    }
}
