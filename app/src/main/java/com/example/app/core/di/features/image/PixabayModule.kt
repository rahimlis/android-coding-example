package com.example.app.core.di.features.image

import com.example.app.features.image.data.mapper.PixabayMapper
import com.example.app.features.image.data.provider.ImageProvider
import com.example.app.features.image.data.provider.PixabayProvider
import com.example.app.features.image.data.remote.PixabayClient
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import retrofit2.Retrofit
import javax.inject.Named

@Module
class PixabayModule {

    @Provides
    @ImageActivityScope
    fun providePixabayMapper() = PixabayMapper()

    @Provides
    @ImageActivityScope
    fun providePixabayClient(
        retrofit: Retrofit.Builder,
        @Named("pixabay.api.url") apiUrl: String
    ) = retrofit
        .baseUrl(apiUrl)
        .build()
        .create(PixabayClient::class.java)

    @Provides
    @ImageActivityScope
    @IntoSet
    fun providePixabayProvider(
        pixabayClient: PixabayClient,
        pixabayMapper: PixabayMapper,
        @Named("pixabay.api.key") apiKey: String
    ): ImageProvider = PixabayProvider(pixabayClient, pixabayMapper, apiKey)
}