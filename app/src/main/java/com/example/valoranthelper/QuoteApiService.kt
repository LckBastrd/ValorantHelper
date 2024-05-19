package com.example.valoranthelper

import retrofit2.Call
import retrofit2.http.GET

interface QuoteApiService {
    @GET("random")
    fun getRandomQuote(): Call<Quote>
}
