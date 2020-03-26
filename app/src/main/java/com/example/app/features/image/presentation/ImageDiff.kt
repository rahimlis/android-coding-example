package com.example.app.features.image.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.app.features.image.domain.Image

class ImageDiff : DiffUtil.ItemCallback<Image>() {

    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}