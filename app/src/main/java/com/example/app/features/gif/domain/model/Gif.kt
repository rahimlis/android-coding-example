package com.example.app.features.gif.domain.model

import java.io.Serializable

data class Gif(
    val url: String?,
    val title: String?,
    val shortUrl: String?,
    val ageRestriction: String?,
    val id: String?,
    val stillUrl: String? = null
) : Serializable