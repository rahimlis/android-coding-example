package com.example.app.features.gif.data.provider

import com.example.app.features.gif.domain.model.Gif
import io.reactivex.Single

interface GifProvider {
    fun searchGifs(query: String): Single<List<Gif>>
    fun randomGif(): Single<Gif>
    fun type(): Type

    enum class Type {
        GIPHY
    }
}