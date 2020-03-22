package com.example.app.features.gif.data.provider.remote.giphy.model

import com.google.gson.annotations.SerializedName

class GiphyUser {

    /**
     * The URL for this user's avatar image.
     * ex: "https://media1.giphy.com/avatars/election2016/XwYrZi5H87o6.gif"
     */
    @SerializedName("avatar_url")
    val avatarUrl: String? = null

    /**
     * The URL for the banner image that appears atop this user's profile page.
     * ex: "https://media4.giphy.com/avatars/cheezburger/XkuejOhoGLE6.jpg"
     */
    @SerializedName("banner_url")
    val bannerUrl: String? = null

    /**
     * The URL for this user's GIPHY profile.
     * ex: "https://giphy.com/cheezburger/"
     */
    @SerializedName("profile_url")
    val profileUrl: String? = null

    /**
     * The username associated with this user.
     * ex: "joecool4000"
     */
    @SerializedName("username")
    val username: String? = null

    /**
     * The display name associated with this user (contains formatting the base username might not).
     * ex: "JoeCool4000"
     */
    @SerializedName("display_name")
    val displayName: String? = null
}