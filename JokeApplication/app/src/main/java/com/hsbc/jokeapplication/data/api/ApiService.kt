package com.hsbc.jokeapplication.data.api

import com.hsbc.jokeapplication.model.Joke
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("jokes")
    @Headers("X-Api-Key: fEWzHLyTYWD8oFYRbncghQ==sKgbuH4noNtqTQbF")
    suspend fun getJokes(@Query("limit") limit: Int): Response<List<Joke>>
}