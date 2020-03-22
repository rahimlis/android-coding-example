package com.example.app.features.gif.data

import com.example.app.features.gif.domain.model.Gif
import com.example.app.features.gif.data.provider.GifProvider
import com.example.app.core.platform.scheduler.SchedulerProvider
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject


open class GifRepository @Inject constructor(
    private val providers: Set<@JvmSuppressWildcards GifProvider>,
    private val schedulerProvider: SchedulerProvider
) {

    open fun search(query: String): Single<List<Gif>> {
        return getProvider(GifProvider.Type.GIPHY).searchGifs(query)
    }

    open fun random(): Flowable<Gif> {
        val gifProvider = getProvider(GifProvider.Type.GIPHY)

        return Flowable
            .interval(0,
                RANDOM_IMAGE_INTERVAL, TimeUnit.SECONDS, schedulerProvider.io())
            .onBackpressureDrop()
            .flatMapSingle({
                gifProvider.randomGif()
            }, false, 1)
    }

    private fun getProvider(which: GifProvider.Type) = providers.filter { it.type() == which }[0]

    companion object {
        const val RANDOM_IMAGE_INTERVAL = 10.toLong()
    }
}