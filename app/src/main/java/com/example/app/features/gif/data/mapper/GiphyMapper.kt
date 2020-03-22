package com.example.app.features.gif.data.mapper

import com.example.app.features.gif.domain.model.Gif
import com.example.app.features.gif.data.provider.remote.giphy.model.GiphyImage
import com.example.app.features.gif.data.provider.remote.giphy.model.GiphyModel

class GiphyMapper {

    fun map(
        giphyModel: GiphyModel,
        size: GiphyImage.Size = GiphyImage.Size.FIXED_HEIGHT
    ) = Gif(
        url = giphyModel.images?.get(size.value)?.url,
        title = giphyModel.title,
        ageRestriction = giphyModel.rating,
        shortUrl = giphyModel.bitlyUrl,
        id = giphyModel.id,
        stillUrl = giphyModel.images?.get(GiphyImage.Size.FIXED_HEIGHT_SMALL_STILL.value)?.url
    )

    fun map(
        giphyModels: List<GiphyModel>,
        size: GiphyImage.Size = GiphyImage.Size.FIXED_HEIGHT
    ) = giphyModels.map { map(it, size) }

}