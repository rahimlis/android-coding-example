package com.example.app.features.image.domain

data class Image(
    val imageThumbUrl: String,
    val imageUrl: String,
    val authorName: String,
    val tags: List<String>
)