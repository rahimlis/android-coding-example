package com.example.app.features.image.data.provider

import com.example.app.features.image.domain.Image
import io.reactivex.Single

interface ImageProvider {
    fun searchImages(query: String): Single<List<Image>>
    fun type(): Type

    enum class Type {
        PIXABAY,
        CACHE
    }
}