package com.example.app.features.gif.domain

import com.example.app.R
import com.example.app.core.common.ProducerUseCase
import com.example.app.core.common.Resource
import com.example.app.core.platform.scheduler.SchedulerProvider
import com.example.app.features.gif.data.GifRepository
import com.example.app.features.gif.domain.model.Gif
import io.reactivex.Flowable
import javax.inject.Inject

class SubscribeToRandomGif @Inject constructor(
    private val gifRepository: GifRepository,
    private val schedulerProvider: SchedulerProvider
) : ProducerUseCase<Flowable<Resource<Gif>>>() {

    override fun run(): Flowable<Resource<Gif>> {
        return gifRepository.random()
            .subscribeOn(schedulerProvider.single())
            .observeOn(schedulerProvider.ui())
            .map { gif -> mapToResource(gif) }
    }

    private fun <T> mapToResource(t: T): Resource<T> {
        return if (t == null)
            Resource.Error(R.string.message_error_fetching_random, null)
        else
            Resource.Success(t)
    }
}