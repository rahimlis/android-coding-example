package com.example.app.core.platform.ui.widget.image

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import javax.inject.Inject

class GlideLoader @Inject
constructor(private val requestManager: RequestManager) :
    ImageLoader {

    override fun clear(view: ImageView?) {
        if (view == null) return
        requestManager.clear(view)
    }

    override fun loadImageIntoView(path: String, view: ImageView?) {
        if (view == null) return
        requestManager.load(path)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(errorImage())
            .into(view)
    }

    override fun loadImageIntoView(resourceId: Int, view: ImageView?) {
        if (view == null) return
        requestManager.load(resourceId)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(errorImage())
            .into(view)
    }
}
