package com.example.app.core.di.module

import com.example.app.core.di.module.gif.GiphyModule
import com.example.app.core.di.module.gif.MainModule
import com.example.app.core.di.scopes.MainScope
import com.example.app.features.gif.presentation.GifViewActivity
import com.example.app.features.gif.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {

    @ContributesAndroidInjector(
        modules = [
            MainModule::class,
            GiphyModule::class,
            ViewModelBuilderModule::class
        ]
    )
    @MainScope
    fun contributeMainActivity(): MainActivity


    @ContributesAndroidInjector(
        modules = []
    )
    fun contributeGifViewActivity(): GifViewActivity

}
