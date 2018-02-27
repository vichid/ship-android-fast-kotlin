package com.example.myapplication.login

import android.arch.lifecycle.ViewModel
import com.example.myapplication.di.ViewModelFactoryModuleBinding
import com.example.myapplication.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModuleBinding::class])
abstract class LoginModuleBinding {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}