package com.example.app.features.image.presentation

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.core.common.Resource
import com.example.app.core.common.Status
import com.example.app.core.platform.arch.ViewModelFactory
import com.example.app.core.platform.extensions.hide
import com.example.app.core.platform.extensions.show
import com.example.app.core.platform.ui.base.BaseActivity
import com.example.app.features.image.domain.Image
import kotlinx.android.synthetic.main.activity_image.*
import timber.log.Timber
import javax.inject.Inject

class ImageActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var imageListAdapter: ImageListAdapter
    @Inject lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var imageViewModel: ImageViewModel
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        imageViewModel = ViewModelProvider(this, viewModelFactory).get(ImageViewModel::class.java)
        imageViewModel.getImages().observe(this, Observer(this::observeImages))
        textViewRetry?.setOnClickListener { imageViewModel.retry() }
        setupImageRecycler()
        imageViewModel.query("fruits")
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_image, menu)
        configureSearch(menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun configureSearch(menu: Menu?) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchableInfo = searchManager.getSearchableInfo(componentName)
        val menuItem = menu?.findItem(R.id.menu_search)
        searchView = menuItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.message_search)
        searchView.setOnQueryTextListener(onQueryTextListener())
        searchView.setSearchableInfo(searchableInfo)
    }

    private fun setupImageRecycler() {
        imageListAdapter.onItemClick = this::onImageItemClick
        recyclerView?.adapter = imageListAdapter
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = null;
    }

    private fun onImageItemClick(image: Image) {
        Toast.makeText(this, "Clicked ${image.authorName}'s image", Toast.LENGTH_SHORT).show()
    }

    private fun observeImages(resource: Resource<List<Image>>) {
        when (resource.status) {
            Status.SUCCESS -> onImageFetchSuccess(resource.data)
            Status.LOADING -> setLoading(true)
            Status.ERROR -> onError(resource.message)
        }
    }

    private fun setLoading(isLoading: Boolean) {
        progressBar?.isVisible = isLoading
        if (isLoading) {
            layoutError?.hide()
            recyclerView?.hide()
        }
    }

    private fun onError(message: Int?) {
        message?.let {
            Timber.e("Error occurred ${getString(it)}")
            textViewErrorMessage?.text = getString(it)
        }
        textViewRetry?.show()
        recyclerView?.hide()
        layoutError?.show()
        progressBar?.hide()
    }

    private fun onImageFetchSuccess(data: List<Image>?) {
        setLoading(false)
        if (data.isNullOrEmpty()) {
            textViewErrorMessage?.text = getString(R.string.message_no_results)
            textViewRetry?.hide()
            layoutError.show()
            return
        }

        imageListAdapter.submitList(data)

        recyclerView?.show()
        layoutError.hide()
    }

    private fun onQueryTextListener() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let { imageViewModel.query(query) }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    }


    override fun layoutRes(): Int = R.layout.activity_image
}