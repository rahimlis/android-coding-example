package com.example.app.features.image.data.mapper

import com.example.app.features.image.data.remote.PixabayImage
import com.example.app.features.image.data.remote.PixabayResponse
import com.example.app.features.image.domain.Image

class PixabayMapper {

    fun mapToImages(pixabayResponse: PixabayResponse): List<Image> {
        return pixabayResponse.hits.map { mapToImage(it) }
    }

    fun mapToImage(pixabayImage: PixabayImage): Image {
        return Image(
            imageThumbUrl = pixabayImage.previewURL,
            imageUrl = pixabayImage.largeImageURL,
            authorName = pixabayImage.user,
            tags = mapTags(pixabayImage.tags)
        )
    }

    private fun mapTags(tags: String): List<String> {
        return tags.split(",")
    }
}