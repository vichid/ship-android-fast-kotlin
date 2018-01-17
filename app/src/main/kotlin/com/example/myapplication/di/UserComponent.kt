package com.example.myapplication.di

import com.example.myapplication.login.model.User
import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

@UserScoped
@Subcomponent(modules = [UserBindingModule::class])
interface UserComponent : AndroidInjector<DaggerApplication> {

    fun inject(userManager: UserManager)

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun user(user: User): Builder

        @BindsInstance
        fun token(token: String): Builder

        fun build(): UserComponent
    }
}