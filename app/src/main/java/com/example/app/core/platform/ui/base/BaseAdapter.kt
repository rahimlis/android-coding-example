package com.example.app.core.platform.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Base adapter containing boilerplate code for the most of recyclerView adapters
 */
abstract class BaseAdapter<M, H : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<M>,
    @LayoutRes private val resourceId: Int
) : ListAdapter<M, H>(diffCallback) {

    private var onListChangedListener: OnListChangedListener<M>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        val view = LayoutInflater.from(parent.context)
            .inflate(resourceId(viewType), parent, false)
        return getViewHolder(view, viewType)
    }

    @LayoutRes
    protected fun resourceId(viewType: Int): Int {
        return resourceId
    }

    override fun onBindViewHolder(holder: H, position: Int) {

        val model = getItem(position) ?: return

        bindData(holder, model)
    }

    override fun onCurrentListChanged(previousList: List<M>, currentList: List<M>) {
        super.onCurrentListChanged(previousList, currentList)
        onListChangedListener?.onCurrentListChanged(currentList)
    }

    protected abstract fun getViewHolder(view: View, type: Int): H

    protected abstract fun bindData(holder: H, model: M)

    fun setOnListChangedListener(onListChangedListener: OnListChangedListener<M>) {
        this.onListChangedListener = onListChangedListener
    }

    fun isEmpty(): Boolean {
        return this.currentList.size == 0
    }

    interface OnListChangedListener<M> {
        fun onCurrentListChanged(currentList: List<M>)
    }

}
