package com.example.app.presentation.di.factories

import android.app.Activity
import com.example.app.presentation.AndroidApplication
import com.example.app.presentation.di.components.ActivityComponent
import com.example.app.presentation.di.modules.ActivityModule

object ActivityComponentFactory {

    fun create(activity: Activity): ActivityComponent {
        return AndroidApplication[activity].component.plus(ActivityModule(activity))
    }
}