package com.example.app.features.gif.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.app.core.common.Resource
import com.example.app.core.common.Status
import com.example.app.features.gif.data.GifRepository
import com.example.app.features.gif.domain.model.Gif
import com.example.app.core.platform.scheduler.TestSchedulerProvider
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class MainViewModelTest {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var gifRepository: GifRepository

    @Mock
    private lateinit var randomGifObserver: Observer<Resource<Gif>>

    @Mock
    private lateinit var searchObserver: Observer<Resource<List<Gif>>>

    @Captor
    private val randomGifCaptor: ArgumentCaptor<Resource<Gif>>? = null

    @Captor
    private val searchCaptor: ArgumentCaptor<Resource<List<Gif>>>? = null


    private val testScheduler = TestScheduler()
    private var schedulerProvider = TestSchedulerProvider(testScheduler)

    lateinit var viewModel: MainViewModel


    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testSearchedGifsSuccess() {
        //setup
        val expectedGif = Gif("url", "title", "short", "a", "id")
        val expectedList = listOf(expectedGif)
        `when`(gifRepository.search("str")).thenReturn(Single.just(expectedList))
        `when`(gifRepository.random()).thenReturn(just(expectedGif))
        viewModel = MainViewModel(gifRepository, schedulerProvider)

        //when
        viewModel.getSearchedGifs().observeForever(searchObserver)
        viewModel.query("str")

        //then
        verify(searchObserver, times(1)).onChanged(searchCaptor?.capture())
        assertTrue(searchCaptor?.value?.status == Status.LOADING)

        testScheduler.triggerActions()

        verify(searchObserver, times(2)).onChanged(searchCaptor?.capture())
        assertEquals(searchCaptor?.value?.status, Status.SUCCESS)
        assertEquals(searchCaptor?.value?.data, expectedList)
    }


    @Test
    fun testSearchedGifsError() {
        //setup
        val expectedGif = Gif("url", "title", "short", "a", "id")
        val expectedList = listOf(expectedGif)
        `when`(gifRepository.search("str")).thenReturn(Single.error(java.lang.Exception()))
        `when`(gifRepository.random()).thenReturn(just(expectedGif))
        viewModel = MainViewModel(gifRepository, schedulerProvider)

        //when
        viewModel.getSearchedGifs().observeForever(searchObserver)
        viewModel.query("str")

        //then
        verify(searchObserver, times(1)).onChanged(searchCaptor?.capture())
        assertTrue(searchCaptor?.value?.status == Status.LOADING)

        testScheduler.triggerActions()

        verify(searchObserver, times(2)).onChanged(searchCaptor?.capture())
        assertEquals(searchCaptor?.value?.status, Status.ERROR)
    }

    @Test
    fun testRandomGifSuccess() {

        //setup
        val expectedGif = Gif("url", "title", "short", "a", "id")
        `when`(gifRepository.random()).thenReturn(just(expectedGif))
        viewModel = MainViewModel(gifRepository, schedulerProvider)

        //when
        viewModel.getRandomGif().observeForever(randomGifObserver)

        //then
        verify(randomGifObserver, times(1)).onChanged(randomGifCaptor?.capture())
        assertTrue(randomGifCaptor?.value?.status == Status.LOADING)

        testScheduler.triggerActions()

        verify(randomGifObserver, times(2)).onChanged(randomGifCaptor?.capture())
        assertEquals(randomGifCaptor?.value?.status, Status.SUCCESS)
        assertEquals(randomGifCaptor?.value?.data, expectedGif)

    }

    @Test
    fun testRandomGifError() {

        //setup
        `when`(gifRepository.random()).thenReturn(Flowable.error(java.lang.Exception()))
        viewModel = MainViewModel(gifRepository, schedulerProvider)

        //when
        viewModel.getRandomGif().observeForever(randomGifObserver)

        //then
        verify(randomGifObserver, times(1)).onChanged(randomGifCaptor?.capture())
        assertTrue(randomGifCaptor?.value?.status == Status.LOADING)

        testScheduler.triggerActions()

        verify(randomGifObserver, times(2)).onChanged(randomGifCaptor?.capture())
        assertTrue(randomGifCaptor?.value?.status == Status.ERROR)
    }

    private fun just(gif: Gif): Flowable<Gif>? {
        return Flowable.just(gif)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }
}