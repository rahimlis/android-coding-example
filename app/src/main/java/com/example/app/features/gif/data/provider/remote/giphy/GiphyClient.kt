package com.example.app.features.gif.data.provider.remote.giphy

import com.example.app.features.gif.data.provider.remote.giphy.model.GiphyRandomResponse
import com.example.app.features.gif.data.provider.remote.giphy.model.GiphySearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyClient {

    @GET("/v1/gifs/search")
    fun search(
        @Query("q") query: String,
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0,
        @Query("rating") rating: String? = null,
        @Query("lang") lang: String = "en",
        @Query("random_id") randomId: String? = null
    ): Single<GiphySearchResponse>

    @GET("/v1/gifs/random")
    fun random(
        @Query("api_key") apiKey: String,
        @Query("tag") tag: String? = null,
        @Query("rating") rating: String? = null,
        @Query("random_id") randomId: String? = null
    ): Single<GiphyRandomResponse>
}