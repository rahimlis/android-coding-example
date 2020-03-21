package com.example.app.core.di.module

import android.app.Application
import android.content.Context

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Provides Application wide context
 */
@Module
interface ContextModule {
    @Binds
    @Singleton
    fun provideApplicationContext(application: Application): Context
}
