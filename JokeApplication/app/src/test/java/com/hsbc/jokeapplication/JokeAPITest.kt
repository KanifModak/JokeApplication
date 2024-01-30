package com.hsbc.jokeapplication

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hsbc.jokeapplication.data.api.ApiService
import com.hsbc.jokeapplication.data.api.RetrofitBuilder
import com.hsbc.jokeapplication.model.Joke
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeAPITest {
    private lateinit var server: MockWebServer//Fake server from square lib
    private lateinit var api: ApiService
    private  val BASE_URL = "https://api.api-ninjas.com/v1/"

    @Before//Using JUnit5
    fun setUp() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url("/"))//Pass any base url like this
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)

        //api= RetrofitBuilder.apiService
    }
    @Test
    fun `getFeedbackQuestions, returns Success`()= runTest() {
        val dto = Joke("Dummy msg 1")//The object I want back as response
        val gson: Gson = GsonBuilder().create()
        val json = gson.toJson(dto)!!//Conver the object into json string using GSON
        val res = MockResponse()//Make a fake response for our server call
        res.setBody(json)//set the body of the fake response as the json string you are expecting as a response
        res.setBody(res.getBody().toString().replace("text="," "))
        server.enqueue(res)//add it in the server response queue

        val data = api.getJokes(5)//make the call to our fake server(as we are using fake base url)
        server.takeRequest()//let the server take the request

       // assertEquals(data.body(), dto)//the data you are getting as the call response should be same
    }

    @After
    fun afterEach() {
        server.shutdown()
    }
}