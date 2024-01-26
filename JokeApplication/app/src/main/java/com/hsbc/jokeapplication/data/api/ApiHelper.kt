package com.hsbc.jokeapplication.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getJokes(limit: Int) = apiService.getJokes(limit)
}