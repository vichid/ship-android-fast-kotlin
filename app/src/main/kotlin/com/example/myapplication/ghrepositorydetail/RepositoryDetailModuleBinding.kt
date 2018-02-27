package com.example.myapplication.ghrepositorydetail

import android.arch.lifecycle.ViewModel
import com.example.myapplication.di.ViewModelFactoryModuleBinding
import com.example.myapplication.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModuleBinding::class])
abstract class RepositoryDetailModuleBinding {

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryDetailViewModel::class)
    abstract fun bindRepositoryDetailViewModel(viewModel: RepositoryDetailViewModel): ViewModel
}