package com.example.app.features.gif

import com.example.app.features.gif.domain.model.Gif
import com.example.app.features.gif.data.provider.MockGiphyProvider
import com.example.app.core.platform.scheduler.TestSchedulerProvider
import com.example.app.features.gif.data.GifRepository
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import java.util.concurrent.TimeUnit

class GifRepositoryTest {


    private val testScheduler = TestScheduler()
    private var schedulerProvider = TestSchedulerProvider(testScheduler)
    private var gifProviders = setOf(MockGiphyProvider())
    private val gifRepository: GifRepository =
        GifRepository(
            gifProviders,
            schedulerProvider
        )

    @Test
    fun testThatRandomGenerates() {
        val subscriber = TestSubscriber<Gif>()
        gifRepository.random()
            .subscribeOn(testScheduler)
            .subscribe(subscriber)

        //no errors or values in the beginning
        subscriber.assertNoErrors()
        subscriber.assertNoValues()

        //should have single element equal to mock value after 1 second
        testScheduler.advanceTimeBy(0, TimeUnit.SECONDS)
        subscriber.assertValue { it == Gif("url", "title", "shortUrl", "age", "id") }

        testScheduler.advanceTimeBy(2 * GifRepository.RANDOM_IMAGE_INTERVAL, TimeUnit.SECONDS)
        subscriber.assertValueCount(3)

        testScheduler.advanceTimeBy(2 * GifRepository.RANDOM_IMAGE_INTERVAL, TimeUnit.SECONDS)
        subscriber.assertValueCount(5)

        subscriber.assertNoErrors()
    }

}