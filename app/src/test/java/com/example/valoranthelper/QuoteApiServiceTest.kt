package com.example.valoranthelper

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class QuoteApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: QuoteApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getRandomQuote returns expected quote`() {
        val mockResponse = MockResponse()
            .setBody("""{"content": "To be or not to be", "author": "William Shakespeare"}""")
        mockWebServer.enqueue(mockResponse)

        val response = apiService.getRandomQuote().execute()
        val quote = response.body()

        assertNotNull(quote)
        assertEquals("To be or not to be", quote!!.content)
        assertEquals("William Shakespeare", quote.author)
    }
}
