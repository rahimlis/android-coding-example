package com.example.app.features.gif.data.provider

import com.example.app.features.gif.domain.model.Gif
import io.reactivex.Single

class MockGiphyProvider : GifProvider {
    override fun searchGifs(query: String): Single<List<Gif>> {
        return Single.just(listOf(Gif("url", "title", "shortUrl", "age", "id")))
    }

    override fun randomGif(): Single<Gif> {
        return Single.just(Gif("url", "title", "shortUrl", "age", "id"))
    }

    override fun type(): GifProvider.Type = GifProvider.Type.GIPHY

}