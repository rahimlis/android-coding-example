package com.example.app.features.gif.presentation

import android.os.Bundle
import android.view.MenuItem
import com.example.app.core.platform.ui.base.BaseActivity
import com.example.app.R
import com.example.app.features.gif.domain.model.Gif
import com.example.app.core.platform.extensions.show
import com.example.app.core.platform.ui.widget.image.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_gif.*
import javax.inject.Inject


class GifViewActivity : BaseActivity() {

    @Inject lateinit var imageLoader: ImageLoader


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showHomeButton(true)
        setDisplayHomeAsUpEnabled(true)

        val gif = intent.getSerializableExtra("gif") as Gif?
        displayGif(gif)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    private fun displayGif(gif: Gif?) {
        layoutGif?.show()
        gif?.url?.let {
            imageLoader.clear(imageViewGif)
            imageLoader.loadImageIntoView(gif.url, imageViewGif)
        }
        val title =
            if (gif?.title.isNullOrBlank()) getString(R.string.message_no_title) else gif?.title

        title?.let { setToolbarText(it) }
        textViewGifTitle?.text = title
        textViewRating?.text = gif?.ageRestriction
        textViewGifUrl?.text = gif?.shortUrl
        textViewMainLabel?.text = getString(R.string.message_random_gif)
    }

    override fun layoutRes(): Int = R.layout.activity_gif_view

}
