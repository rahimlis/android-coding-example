package com.example.app.features.gif.data.provider

import com.example.app.features.gif.data.mapper.GiphyMapper
import com.example.app.features.gif.domain.model.Gif
import com.example.app.features.gif.data.provider.remote.giphy.GiphyClient
import io.reactivex.Single
import javax.inject.Inject

class GiphyProvider @Inject constructor(
    private val giphyClient: GiphyClient,
    private val giphyMapper: GiphyMapper,
    private val apiKey: String
) : GifProvider {

    override fun type(): GifProvider.Type {
        return GifProvider.Type.GIPHY
    }

    override fun searchGifs(query: String): Single<List<Gif>> {
        return giphyClient.search(query, apiKey)
            .map { r -> r.data?.let { giphyMapper.map(it) } }
    }

    override fun randomGif(): Single<Gif> {
        return giphyClient.random(apiKey)
            .map { r -> r.data?.let { giphyMapper.map(it) } }
    }
}