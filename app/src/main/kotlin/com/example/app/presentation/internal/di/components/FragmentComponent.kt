package com.example.app.presentation.internal.di.components

import com.example.app.presentation.internal.di.modules.FragmentModule
import com.example.app.presentation.internal.di.scope.PerFragment
import dagger.Component

@PerFragment
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent
