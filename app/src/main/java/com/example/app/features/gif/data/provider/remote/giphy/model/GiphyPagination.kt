package com.example.app.features.gif.data.provider.remote.giphy.model

import com.google.gson.annotations.SerializedName

class GiphyPagination {

    /**
     *  Position in pagination.
     *  ex: 2591
     */
    @SerializedName("offset")
    val offset: Int? = 0

    /**
     *  Total number of items available (not returned on every endpoint).
     *  ex: 250
     */
    @SerializedName("total_count")
    val totalCount: Int? = 0

    /**
     *  Total number of items returned.
     *  ex: 25
     */
    @SerializedName("count")
    val count: Int? = 0
}