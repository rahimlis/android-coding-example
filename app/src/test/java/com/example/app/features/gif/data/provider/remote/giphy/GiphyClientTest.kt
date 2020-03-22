package com.example.app.features.gif.data.provider.remote.giphy

import com.example.app.TestUtils
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection


class GiphyClientTest {

    private val mockWebServer = MockWebServer()

    private lateinit var giphyClient: GiphyClient


    @Before
    fun setup() {
        mockWebServer.start()
        giphyClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(OkHttpClient.Builder().build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GiphyClient::class.java)
    }

    @Test
    fun testGiphySearchEndpoint() {
        //setup
        val responseBody = TestUtils.loadResourceAsString("/giphy_search_response.json")

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .addHeader("Content-Type", "application/json; charset=UTF-8")
            .setBody(responseBody)
        mockWebServer.enqueue(response)

        //when
        val actualResponse = giphyClient.search("query","").blockingGet()

        //then:
        val actualGiphyData = actualResponse.data?.get(0)
        val actualGiphyPagination = actualResponse.pagination
        val actualGiphyMeta = actualResponse.meta

        assertNotNull(actualGiphyData)
        assertNotNull(actualGiphyData?.url)
        assertEquals("kicking field goal GIF", actualGiphyData?.title)
        assertEquals("https://gph.is/1dN4ujx", actualGiphyData?.bitlyUrl)
        assertEquals("g", actualGiphyData?.rating)
        assertEquals(120907, actualGiphyPagination?.totalCount)
        assertEquals(200, actualGiphyMeta?.status)
    }

    @Test
    fun testGiphyRandomEndpoint() {
        //setup
        val responseBody = TestUtils.loadResourceAsString("/giphy_random_response.json")

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .addHeader("Content-Type", "application/json; charset=UTF-8")
            .setBody(responseBody)
        mockWebServer.enqueue(response)

        //when
        val actualResponse = giphyClient.random("").blockingGet()

        //then:
        val actualGiphyData = actualResponse.data
        val actualGiphyMeta = actualResponse.meta

        assertNotNull(actualGiphyData)
        assertNotNull(actualGiphyData?.url)
        assertEquals("happy fired up GIF by City of Orlando", actualGiphyData?.title)
        assertEquals("https://gph.is/g/aNrWeJa", actualGiphyData?.bitlyUrl)
        assertEquals("g", actualGiphyData?.rating)
        assertEquals(200, actualGiphyMeta?.status)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}