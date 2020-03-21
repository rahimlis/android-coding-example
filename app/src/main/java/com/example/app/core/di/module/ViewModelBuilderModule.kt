package com.example.app.core.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.app.core.platform.arch.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelBuilderModule {
    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}