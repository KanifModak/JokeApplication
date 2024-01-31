package com.hsbc.jokeapplication

import com.hsbc.jokeapplication.data.api.ApiHelper
import com.hsbc.jokeapplication.data.api.JokeRepository
import com.hsbc.jokeapplication.model.Joke
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class JokeRepositoryTest {
    private val apiHelper: ApiHelper = mockk()
    private lateinit var repository: JokeRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = JokeRepository(apiHelper)
    }

    @Test
    fun `when repository call then return api success`() {
        val jokesList = arrayListOf<Joke>()
        jokesList.add(Joke("Dummy joke 1"))
        jokesList.add(Joke("Dummy joke 2"))
        jokesList.add(Joke("Dummy joke 3"))

        val actualResponse: retrofit2.Response<List<Joke>> = retrofit2.Response.success(jokesList)
        var response: retrofit2.Response<List<Joke>>? = null

        coEvery {
            apiHelper.getJokes(5)
        } returns actualResponse

        runBlocking { response = repository.getJokes(5) }

        Assert.assertEquals(response?.body(), jokesList)

    }
}