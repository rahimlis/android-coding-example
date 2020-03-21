package com.example.app.core.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.app.R
import com.example.app.core.platform.scheduler.DefaultSchedulerProvider
import com.example.app.core.platform.scheduler.SchedulerProvider
import com.example.app.core.platform.ui.widget.image.GlideLoader
import com.example.app.core.platform.ui.widget.image.ImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides Application wide configuration and dependencies
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideGlideRequestOptions() = RequestOptions.errorOf(R.drawable.error_drawable)

    @Provides
    @Singleton
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ) = Glide.with(application)
        .setDefaultRequestOptions(requestOptions)


    @Provides
    @Singleton
    fun provideImageLoader(requestManager: RequestManager): ImageLoader =
        GlideLoader(
            requestManager
        )

    @Provides
    @Singleton
    fun provideResources(context: Context) = context.resources


    @Provides
    @Singleton
    fun provideViewModelFactory(application: Application) =
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)

    @Provides
    @Singleton
    fun provideDefaultSchedulerProvider(): SchedulerProvider = DefaultSchedulerProvider()
}
