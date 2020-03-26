package com.example.app.features.image.data

import com.example.app.features.image.data.provider.ImageProvider
import com.example.app.features.image.domain.Image
import io.reactivex.Single
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val providers: Set<@JvmSuppressWildcards ImageProvider>
) {

    fun findImages(query: String): Single<List<Image>> {
        return getProvider(ImageProvider.Type.PIXABAY).searchImages(query)
    }

    private fun getProvider(which: ImageProvider.Type) = providers.filter { it.type() == which }[0]
}