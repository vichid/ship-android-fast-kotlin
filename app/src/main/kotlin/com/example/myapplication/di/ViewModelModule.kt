package com.example.myapplication.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.myapplication.ghrepositories.RepositoryListViewModel
import com.example.myapplication.ghrepositorydetail.RepositoryDetailViewModel
import com.example.myapplication.home.HomeViewModel
import com.example.myapplication.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryListViewModel::class)
    abstract fun bindRepositoryListViewModel(viewModel: RepositoryListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryDetailViewModel::class)
    abstract fun bindRepositoryDetailViewModel(viewModel: RepositoryDetailViewModel): ViewModel
}