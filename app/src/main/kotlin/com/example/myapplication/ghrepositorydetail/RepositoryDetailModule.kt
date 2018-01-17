package com.example.myapplication.ghrepositorydetail

import com.example.myapplication.di.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RepositoryDetailModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun repositoryDetailFragment(): RepositoryDetailFragment
}