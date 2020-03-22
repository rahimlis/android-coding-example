package com.example.app.features.gif.domain

import com.example.app.R
import com.example.app.core.common.Resource
import com.example.app.core.common.UseCase
import com.example.app.core.platform.scheduler.SchedulerProvider
import com.example.app.features.gif.data.GifRepository
import com.example.app.features.gif.domain.model.Gif
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

class SearchGif @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val gifRepository: GifRepository
) : UseCase<SearchGif.Params, Single<Resource<List<Gif>>>> {

    override fun run(param: Params): Single<Resource<List<Gif>>> {
        return gifRepository.search(param.query)
            .subscribeOn(schedulerProvider.single())
            .observeOn(schedulerProvider.ui())
            .map { list -> mapToResource(list) }
            .onErrorReturn {
                Timber.e("Error occurred while searching.")
                Resource.Error(R.string.message_error_fetching_search, it)
            }.doOnSubscribe(param.onSubscribe)
    }

    private fun <T> mapToResource(t: T): Resource<T> {
        return if (t == null)
            Resource.Error(R.string.message_error_fetching_random, null)
        else
            Resource.Success(t)
    }

    data class Params(
        val query: String,
        val onSubscribe: Consumer<in Disposable>
    )
}