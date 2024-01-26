package com.hsbc.jokeapplication.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hsbc.jokeapplication.data.api.ApiHelper
import com.hsbc.jokeapplication.data.api.JokeRepository

class JokeViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokeViewModel::class.java)) {
            return JokeViewModel(JokeRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}