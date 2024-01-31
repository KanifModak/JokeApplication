package com.hsbc.jokeapplication


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hsbc.jokeapplication.data.api.JokeRepository
import com.hsbc.jokeapplication.data.viewmodels.JokeViewModel
import com.hsbc.jokeapplication.model.Joke
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class JokeViewModelTest {

    private val repository: JokeRepository = mockk()
    private lateinit var viewModel: JokeViewModel
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
        viewModel = JokeViewModel(repository)
    }

    @Test
    fun `should emit error object when joke api response error`() {
        val body: ResponseBody = mockk()
        val errorCode = 400
        val actualResponse: retrofit2.Response<List<Joke>> =
            retrofit2.Response.error(errorCode, body)

        coEvery {
            repository.getJokes(5)
        } returns actualResponse

        viewModel.getJokes(5)

        coVerify {
            repository.getJokes(5)
        }
        Assert.assertEquals(viewModel.errorMessageLiveData.value?.toInt(), 400)

    }

    @Test
    fun `should emit success object when joke api response success`() {
        val jokesList = arrayListOf<Joke>()
        jokesList.add(Joke("Dummy joke 1"))
        jokesList.add(Joke("Dummy joke 2"))
        jokesList.add(Joke("Dummy joke 3"))

        val actualResponse: retrofit2.Response<List<Joke>> = retrofit2.Response.success(jokesList)

        coEvery {
            repository.getJokes(5)
        } returns actualResponse

        viewModel.getJokes(5)

        coVerify {
            repository.getJokes(5)
        }
        Assert.assertEquals(viewModel.jokeLiveData.value, jokesList)
        Assert.assertEquals(viewModel.jokeLiveData.value?.get(0)?.jokeMsg, "Dummy joke 1")
    }



    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}