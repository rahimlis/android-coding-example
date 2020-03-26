package com.example.app.features.image.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.app.R
import com.example.app.core.common.Resource
import com.example.app.features.image.domain.FindImagesUseCase
import com.example.app.features.image.domain.Image
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ImageViewModel @Inject constructor(
    private val findImages: FindImagesUseCase
) : ViewModel() {

    private val images: MediatorLiveData<Resource<List<Image>>> = MediatorLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var query: String? = null

    fun getImages(): LiveData<Resource<List<Image>>> = images

    fun query(query: String) {
        this.query = query
        val disposable = findImages(query)
            .doOnSubscribe { this.images.value = Resource.Loading() }
            .subscribe({
                this.images.value = Resource.Success(it)
            }, {
                this.images.value =
                    Resource.Error(R.string.message_error_fail_fetching_images_from_remote)
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun retry() {

    }
}