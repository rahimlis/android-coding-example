package com.example.app.features.gif.data.provider.remote.giphy.model

import com.google.gson.annotations.SerializedName

class GiphyModel {

    /**
     * By default, this is almost always GIF. "gif"
     */
    @SerializedName("type")
    val type: String? = null

    /**
     * This GIF's unique ID
     * ex: "YsTs5ltWtEhnq"
     */
    @SerializedName("id")
    val id: String? = null

    /**
     * The unique slug used in this GIF's URL
     * ex: "confused-flying-YsTs5ltWtEhnq"
     */
    @SerializedName("slug")
    val slug: String? = null

    /**
     * The unique URL for this GIF
     * ex: "http://giphy.com/gifs/confused-flying-YsTs5ltWtEhnq"
     */
    @SerializedName("url")
    val url: String? = null

    /**
     * The unique bit.ly URL for this GIF
     * ex: "http://gph.is/1gsWDcL"
     */
    @SerializedName("bitly_url")
    val bitlyUrl: String? = null

    /**
     * A URL used for embedding this GIF
     * ex: "http://giphy.com/embed/YsTs5ltWtEhnq"
     */
    @SerializedName("embed_url")
    val embedUrl: String? = null


    /**
     * The username this GIF is attached to, if applicable
     * ex: "JoeCool4000"
     */
    @SerializedName("username")
    val username: String? = null


    /**
     * The page on which this GIF was found
     * ex: "http://www.reddit.com/r/reactiongifs/comments/1xpyaa/superman_goes_to_hollywood/"
     */
    @SerializedName("source")
    val source: String? = null

    /**
     * The MPAA-style rating for this content.
     * Examples include Y, G, PG, PG-13 and R	"g"
     */
    @SerializedName("rating")
    val rating: String? = null

    /**
     * Currently unused
     */
    @SerializedName("content_url")
    val contentUrl: String? = null

    /**
     * An object containing data about the user associated with this GIF, if applicable.
     */
    @SerializedName("user")
    val user: GiphyUser? = null

    /**
     * The top level domain of the source URL.
     * ex: "cheezburger.com"
     */
    @SerializedName("source_tld")
    val sourceTld: String? = null

    /**
     * The URL of the webpage on which this GIF was found.
     * ex: "http://cheezburger.com/5282328320"
     */
    @SerializedName("source_post_url")
    val sourcePostUrl: String? = null

    /**
     * The date on which this GIF was last updated.
     * ex: "2013-08-01 12:41:48"
     */
    @SerializedName("update_datetime")
    val updateDatetime: String? = null

    /**
     * The date this GIF was added to the GIPHY database.
     * ex: "2013-08-01 12:41:48"
     */
    @SerializedName("create_datetime")
    val createDatetime: String? = null

    /**
     * The creation or upload date from this GIF's source.
     * ex: "2013-08-01 12:41:48"
     */
    @SerializedName("import_datetime")
    val importDatetime: String? = null

    /**
     * The date on which this gif was marked trending, if applicable.
     * ex: "2013-08-01 12:41:48"
     */
    @SerializedName("trending_datetime")
    val trendingDatetime: String? = null

    /**
     * An object containing data for various available formats and sizes of this GIF.
     */
    @SerializedName("images")
    val images: LinkedHashMap<String, GiphyImage>? = null

    /**
     * The title that appears on giphy.com for this GIF.
     * ex: "Happy Dancing GIF"
     */
    @SerializedName("title")
    val title: String? = null

}