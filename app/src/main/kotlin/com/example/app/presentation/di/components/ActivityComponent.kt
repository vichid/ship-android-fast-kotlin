package com.example.app.presentation.di.components

import com.example.app.presentation.di.modules.ActivityModule
import com.example.app.presentation.di.scopes.PerActivity
import com.example.app.presentation.ui.github.activities.FollowersActivity
import com.example.app.presentation.ui.github.activities.GithubUserActivity
import dagger.Subcomponent

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * [PerActivity]
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(githubUserActivity: GithubUserActivity)

    fun inject(followersActivity: FollowersActivity)
}
