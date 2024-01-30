package com.hsbc.jokeapplication

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModelProvider
import com.hsbc.jokeapplication.data.api.ApiHelper
import com.hsbc.jokeapplication.data.viewmodels.JokeViewModelFactory
import io.mockk.mockk

class ViewModelFactoryTest {

    object ViewModelFactoryOfFactory {
        // The default factory.
        private val apiHelper: ApiHelper = mockk()
        var INSTANCE: ViewModelProvider.Factory = JokeViewModelFactory(apiHelper)
            private set

        // To set the factory during tests.
        @VisibleForTesting
        fun setTestFactory(factory: ViewModelProvider.Factory) {
            INSTANCE = factory
        }
    }
}