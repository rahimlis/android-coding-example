package com.example.app.features.gif.data.provider.remote.giphy.model

import com.google.gson.annotations.SerializedName

class GiphyImage {

    /**
     * The height of this GIF in pixels.
     */
    @SerializedName("height")
    val height: String? = null

    /**
     * The width of this GIF in pixels.
     */
    @SerializedName("width")
    val width: String? = null

    /**
     * The size of this GIF in bytes.
     */
    @SerializedName("size")
    val size: String? = null

    /**
     * The publicly-accessible direct URL for this GIF for this size of the GIF.
     */
    @SerializedName("url")
    val url: String? = null


    enum class Size(val value: String) {
        FIXED_HEIGHT("fixed_height"),
        FIXED_HEIGHT_SMALL_STILL("fixed_height_small_still"),
        FIXED_WIDTH("fixed_width"),
        ORIGINAL("original")
    }
}