package com.example.app.features.gif.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.R
import com.example.app.core.common.Resource
import com.example.app.core.platform.extensions.toLiveData
import com.example.app.features.gif.domain.SearchGif
import com.example.app.features.gif.domain.SubscribeToRandomGif
import com.example.app.features.gif.domain.model.Gif
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription
import timber.log.Timber
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    private val subscribeToRandomGifUseCase: SubscribeToRandomGif,
    private val searchGif: SearchGif
) : ViewModel() {

    private val searchedGifs: MediatorLiveData<Resource<List<Gif>>> = MediatorLiveData()
    private val randomGif: MediatorLiveData<Resource<Gif>> = MediatorLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var query: String? = null
    private var randomGifSubscription: Subscription? = null

    init {
        subscribeToRandomGif()
    }

    fun getSearchedGifs(): LiveData<Resource<List<Gif>>> {
        return searchedGifs
    }

    fun getRandomGif(): LiveData<Resource<Gif>> {
        return randomGif
    }

    fun retry() {
        if (query.isNullOrEmpty()) {
            subscribeToRandomGif()
        } else {
            query(query!!)
        }
    }

    fun onSearchClosed() {
        Timber.i("Search closed, resubscribe to random gifs")
        query = null
        subscribeToRandomGif()
    }

    fun query(query: String) {
        this.query = query
        randomGifSubscription?.cancel()
        val source = getGifSearch(query)
        searchedGifs.addSource(source) { s ->
            searchedGifs.value = s
            searchedGifs.removeSource(source)
        }
    }

    private fun getGifSearch(query: String): LiveData<Resource<List<Gif>>> {
        return if (query.length >= SEARCH_THRESHOLD) {
            searchedGifs.value = Resource.Loading()
            searchGif(SearchGif.Params(query, Consumer { compositeDisposable.add(it) })).toLiveData()
        } else
            MutableLiveData(Resource.Success(ArrayList()))
    }

    private fun subscribeToRandomGif() {
        val disposable = subscribeToRandomGifUseCase()
            .doOnSubscribe {
                Timber.i("Subscribed to NEW random gif request ")
                randomGif.value = Resource.Loading()
                randomGifSubscription = it
            }
            .subscribe({
                randomGif.value = it
            }, {
                Timber.e("Error occurred while fetching random. Disposing..")
                randomGif.value = Resource.Error(R.string.message_error_fetching_random, it)
            })
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        const val SEARCH_THRESHOLD = 2
    }
}