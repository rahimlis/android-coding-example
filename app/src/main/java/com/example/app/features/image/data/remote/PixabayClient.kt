package com.example.app.features.image.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayClient {
    @GET("/api")
    fun search(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 25,
        @Query("lang") lang: String = "en",
        @Query("rating") imageType: String = "all"
    ): Single<PixabayResponse>
}