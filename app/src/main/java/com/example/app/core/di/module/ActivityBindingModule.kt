package com.example.app.core.di.module

import com.example.app.core.di.features.image.ImageActivityModule
import com.example.app.core.di.features.image.ImageActivityScope
import com.example.app.core.di.features.image.PixabayModule
import com.example.app.features.image.presentation.ImageActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {

    @ContributesAndroidInjector(
        modules = [
            PixabayModule::class,
            ImageActivityModule::class,
            ViewModelBuilderModule::class
        ]
    )
    @ImageActivityScope
    fun contributeImageActivity(): ImageActivity

}