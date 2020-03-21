package com.example.app.core.platform.ui.widget.image

import android.widget.ImageView

import com.example.app.R


interface ImageLoader {
    fun loadImageIntoView(path: String, view: ImageView?)

    fun loadImageIntoView(resourceId: Int, view: ImageView?)

    fun clear(view: ImageView?)

    fun errorImage(): Int {
        return R.drawable.error_drawable
    }
}
