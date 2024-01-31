package com.hsbc.jokeapplication

import com.hsbc.jokeapplication.data.api.RetrofitBuilder
import io.mockk.MockKAnnotations
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RetrofitBuilderTest {
    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `retrofit object is non null`() {
        val apiService= RetrofitBuilder.apiService
        Assert.assertNotNull(apiService)
    }
}