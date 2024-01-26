package com.hsbc.jokeapplication.data.api

import com.hsbc.jokeapplication.model.Joke
import retrofit2.Call
import retrofit2.Response

class JokeRepository(private val apiHelper: ApiHelper) {
    suspend fun getJokes(limit: Int): Response<List<Joke>> = apiHelper.getJokes(limit)
}