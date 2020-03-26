package com.example.app.features.image.data.provider

import com.example.app.features.image.data.mapper.PixabayMapper
import com.example.app.features.image.data.remote.PixabayClient
import com.example.app.features.image.domain.Image
import io.reactivex.Single
import javax.inject.Inject

class PixabayProvider @Inject constructor(
    private val pixabayClient: PixabayClient,
    private val pixabayMapper: PixabayMapper,
    private val apiKey: String
) : ImageProvider {

    override fun searchImages(query: String): Single<List<Image>> {
        return pixabayClient.search(apiKey, query)
            .map { pixabayMapper.mapToImages(it) }
    }

    override fun type(): ImageProvider.Type {
        return ImageProvider.Type.PIXABAY
    }
}