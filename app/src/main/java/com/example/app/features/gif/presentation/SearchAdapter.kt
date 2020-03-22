package com.example.app.features.gif.presentation

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app.features.gif.domain.model.Gif
import com.example.app.core.platform.ui.base.BaseAdapter
import com.example.app.core.platform.ui.widget.image.ImageLoader
import kotlinx.android.synthetic.main.item_search_result.view.*

class SearchAdapter(
    diffCallback: DiffUtil.ItemCallback<Gif>,
    @LayoutRes resourceId: Int,
    private val imageLoader: ImageLoader
) :
    BaseAdapter<Gif, SearchAdapter.SearchViewHolder>(diffCallback, resourceId) {

    var onItemClick: ((Gif) -> Unit)? = null

    override fun getViewHolder(view: View, type: Int) = SearchViewHolder(view)

    override fun bindData(holder: SearchViewHolder, model: Gif) {
        model.stillUrl?.let { imageLoader.loadImageIntoView(it, holder.itemView.imageViewSearch) }
        holder.itemView.setOnClickListener { onItemClick?.invoke(model) }
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}