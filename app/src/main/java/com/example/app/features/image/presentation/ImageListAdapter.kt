package com.example.app.features.image.presentation

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app.core.platform.ui.base.BaseAdapter
import com.example.app.core.platform.ui.widget.image.ImageLoader
import com.example.app.features.image.domain.Image
import kotlinx.android.synthetic.main.item_image.view.*

class ImageListAdapter(
    diffCallback: DiffUtil.ItemCallback<Image>,
    @LayoutRes resourceId: Int,
    private val imageLoader: ImageLoader
) : BaseAdapter<Image, ImageListAdapter.ImageViewHolder>(diffCallback, resourceId) {

    var onItemClick: ((Image) -> Unit)? = null

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getViewHolder(view: View, type: Int): ImageViewHolder = ImageViewHolder(view)

    override fun bindData(holder: ImageViewHolder, model: Image) {
        imageLoader.loadImageIntoView(model.imageThumbUrl, holder.itemView.imageViewItem)
        holder.itemView.textviewTags?.text = model.tags.joinToString(",")
        holder.itemView.textviewUsername?.text = model.authorName
        holder.itemView.setOnClickListener { onItemClick?.invoke(model) }
    }
}