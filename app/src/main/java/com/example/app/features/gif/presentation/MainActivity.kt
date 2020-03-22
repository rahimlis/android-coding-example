package com.example.app.features.gif.presentation

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.app.core.platform.ui.base.BaseActivity
import com.example.app.R
import com.example.app.core.common.Resource
import com.example.app.core.common.Status
import com.example.app.features.gif.domain.model.Gif
import com.example.app.core.platform.extensions.hide
import com.example.app.core.platform.extensions.setVisible
import com.example.app.core.platform.extensions.show
import com.example.app.core.platform.ui.widget.image.ImageLoader
import com.example.app.core.platform.arch.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_gif.*
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var imageLoader: ImageLoader
    @Inject lateinit var searchAdapter: SearchAdapter
    @Inject lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var mainViewModel: MainViewModel
    private lateinit var searchView: SearchView
    private var isGifLoaded = AtomicBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.getRandomGif().observe(this, Observer(this::observeRandomGif))
        mainViewModel.getSearchedGifs().observe(this, Observer(this::observeSearch))
        textViewRetry?.setOnClickListener { mainViewModel.retry() }
        setupSearchRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        configureSearch(menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun configureSearch(menu: Menu?) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchableInfo = searchManager.getSearchableInfo(componentName)
        val menuItem = menu?.findItem(R.id.menu_search)
        menuItem?.setOnActionExpandListener(onActionExpandListener())
        searchView = menuItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.message_search)
        searchView.setOnQueryTextListener(onQueryTextListener())
        searchView.setSearchableInfo(searchableInfo)
    }


    private fun setupSearchRecycler() {
        searchAdapter.onItemClick = this::onSearchItemClick
        recyclerView?.adapter = searchAdapter
        recyclerView?.layoutManager = layoutManager
    }

    private fun onSearchItemClick(gif: Gif) {
        hideKeyboard()
        val intent = Intent(this, GifViewActivity::class.java)
        intent.putExtra("gif", gif)
        startActivity(intent)
    }

    private fun onQueryTextListener() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let { mainViewModel.query(query) }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            Timber.i("new text:$newText.")
            newText?.let { mainViewModel.query(it) }
            return true
        }
    }

    private fun onActionExpandListener(): MenuItem.OnActionExpandListener {
        return object : MenuItem.OnActionExpandListener {

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                mainViewModel.query("")
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                Timber.i("on menu item collapsed")
                searchView.onActionViewCollapsed()
                mainViewModel.onSearchClosed()
                return true
            }

        }
    }

    private fun observeSearch(searchResource: Resource<List<Gif>>) {
        when (searchResource.status) {
            Status.SUCCESS -> onSearchSuccess(searchResource.data)
            Status.LOADING -> setLoading(true)
            Status.ERROR -> onError(searchResource.message)
        }
    }

    private fun onSearchSuccess(data: List<Gif>?) {
        if (data != null) {
            searchAdapter.submitList(data)
        }
        setLoading(false)
        layoutGif?.hide()
        recyclerView?.show()
        layoutError.hide()
        textViewMainLabel.text = getString(R.string.message_search_results)
        textViewMainLabel?.show()
    }


    private fun observeRandomGif(gifResource: Resource<Gif>) {
        when (gifResource.status) {
            Status.SUCCESS -> onRandomGifSuccess(gifResource.data)
            Status.LOADING -> if (!isGifLoaded.get()) setLoading(true)
            Status.ERROR -> onError(gifResource.message)
        }
    }

    private fun onRandomGifSuccess(data: Gif?) {
        displayGif(data)
        setLoading(false)
        isGifLoaded.set(true)
    }

    private fun onError(message: Int?) {
        message?.let {
            Timber.e("Error occurred ${getString(it)}")
            textViewErrorMessage?.text = getString(it)
        }
        recyclerView?.hide()
        layoutError?.show()
        progressBar?.hide()
        layoutGif?.hide()
        textViewMainLabel?.hide()

        imageLoader.clear(imageViewGif)
        isGifLoaded.set(false)
    }

    private fun setLoading(isLoading: Boolean) {
        progressBar?.setVisible(isLoading)
        textViewMainLabel?.setVisible(!isLoading)

        if (isLoading)
            layoutError?.hide()
    }

    private fun displayGif(gif: Gif?) {
        layoutGif?.show()
        gif?.url?.let {
            imageLoader.clear(imageViewGif)
            imageLoader.loadImageIntoView(gif.url, imageViewGif)
        }
        val title =
            if (gif?.title.isNullOrBlank()) getString(R.string.message_no_title) else gif?.title
        textViewGifTitle?.text = title
        textViewRating?.text = gif?.ageRestriction
        textViewGifUrl?.text = gif?.shortUrl
        textViewMainLabel?.text = getString(R.string.message_random_gif)
    }

    override fun layoutRes(): Int = R.layout.activity_main
}