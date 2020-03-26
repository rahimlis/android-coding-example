package com.example.app.features.image.data.remote

import com.google.gson.annotations.SerializedName

data class PixabayImage(
    @SerializedName("id") val id: Int,
    @SerializedName("pageURL") val pageURL: String,
    @SerializedName("type") val type: String,
    @SerializedName("tags") val tags: String,
    @SerializedName("previewURL") val previewURL: String,
    @SerializedName("largeImageURL") val largeImageURL: String,
    @SerializedName("views") val views: Int,
    @SerializedName("downloads") val downloads: Int,
    @SerializedName("favorites") val favorites: Int,
    @SerializedName("likes") val likes: Int,
    @SerializedName("comments") val comments: Int,
    @SerializedName("user") val user: String,
    @SerializedName("userImageURL") val userImageURL: String
)