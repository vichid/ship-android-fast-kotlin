package com.example.myapplication.di

import com.example.myapplication.ghrepositorydetail.RepositoryDetailActivity
import com.example.myapplication.ghrepositorydetail.RepositoryDetailModuleBinding
import com.example.myapplication.home.HomeActivity
import com.example.myapplication.home.HomeModuleBinding
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [HomeModuleBinding::class])
    internal abstract fun homeActivity(): HomeActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [RepositoryDetailModuleBinding::class])
    internal abstract fun repositoryDetailActivity(): RepositoryDetailActivity
}