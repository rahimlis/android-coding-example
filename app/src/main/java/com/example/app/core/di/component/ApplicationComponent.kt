package com.example.app.core.di.component

import android.app.Application
import com.example.app.DemoApp
import com.example.app.core.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        ApplicationModule::class,
        ContextModule::class,
        NetworkModule::class,
        SecretsModule::class
    ]
)

interface ApplicationComponent : AndroidInjector<DemoApp> {

    fun inject(application: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}