package com.hsbc.jokeapplication

import com.hsbc.jokeapplication.data.api.ApiHelper
import com.hsbc.jokeapplication.data.api.ApiService
import com.hsbc.jokeapplication.data.api.JokeRepository
import com.hsbc.jokeapplication.data.viewmodels.JokeViewModel
import com.hsbc.jokeapplication.model.Joke
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import okhttp3.Response
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class APIHelperTest {
    private val apiService: ApiService = mockk()
    private lateinit var apiHelper: ApiHelper

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        apiHelper = ApiHelper(apiService)
    }

    @Test
    fun `when api helper call then return api success`() {
        val jokesList = arrayListOf<Joke>()
        jokesList.add(Joke("Dummy joke 1"))
        jokesList.add(Joke("Dummy joke 2"))
        jokesList.add(Joke("Dummy joke 3"))

        val actualResponse: retrofit2.Response<List<Joke>> = retrofit2.Response.success(jokesList)
        var response: retrofit2.Response<List<Joke>>? = null

        coEvery {
            apiService.getJokes(5)
        } returns actualResponse

        runBlocking { response = apiHelper.getJokes(5) }

        Assert.assertEquals(response?.body(), jokesList)

    }
}