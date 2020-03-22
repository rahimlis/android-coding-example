package com.example.app.core.di.module.gif

import com.example.app.features.gif.data.mapper.GiphyMapper
import com.example.app.features.gif.data.provider.GifProvider
import com.example.app.features.gif.data.provider.GiphyProvider
import com.example.app.features.gif.data.provider.remote.giphy.GiphyClient
import com.example.app.core.di.scopes.MainScope
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import retrofit2.Retrofit
import javax.inject.Named

@Module
class GiphyModule {

    @Provides
    @MainScope
    fun provideGiphyMapper() = GiphyMapper()


    @Provides
    @MainScope
    fun provideGiphyClient(
        retrofit: Retrofit.Builder,
        @Named("giphy.api.url") apiUrl: String
    ) = retrofit
        .baseUrl(apiUrl)
        .build()
        .create(GiphyClient::class.java)

    @Provides
    @MainScope
    @IntoSet
    fun provideGiphyProvider(
        giphyClient: GiphyClient,
        giphyMapper: GiphyMapper,
        @Named("giphy.api.key") apiKey: String
    ): GifProvider = GiphyProvider(giphyClient, giphyMapper, apiKey)

}
