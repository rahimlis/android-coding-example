package com.example.app.features.gif.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.app.features.gif.domain.model.Gif

class GifDiff : DiffUtil.ItemCallback<Gif>() {

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem.id == newItem.id
    }
}