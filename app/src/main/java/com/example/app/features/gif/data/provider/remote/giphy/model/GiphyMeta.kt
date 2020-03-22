package com.example.app.features.gif.data.provider.remote.giphy.model

import com.google.gson.annotations.SerializedName

class GiphyMeta {

    /**
     * HTTP Response Message.
     * ex: "OK"
     */
    @SerializedName("msg")
    val httpMessage: String? = null

    /**
     * HTTP Response Code. (required)
     * ex: 200
     */
    @SerializedName("status")
    val status: Int? = 0

    /**
     * A unique ID paired with this response from the API.
     * ex: "57eea03c72381f86e05c35d2"
     */
    @SerializedName("response_id")
    val responseId: String? = null
}