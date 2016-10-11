package com.example.app.presentation.navigation

import android.app.Activity
import com.example.app.presentation.di.scopes.PerActivity
import com.example.app.presentation.ui.github.activities.GithubUserActivity
import com.example.app.presentation.ui.github.model.Follower
import org.jetbrains.anko.intentFor
import javax.inject.Inject

/**
 * Class used to navigate through the application.
 */
@PerActivity
class Navigator
@Inject
constructor(val activity: Activity) {

    fun navigateToGithubUser(follower: Follower) {
        with(activity) {
            startActivity(intentFor<GithubUserActivity>("follower" to follower))
        }
    }
}
