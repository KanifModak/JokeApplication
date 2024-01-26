package com.hsbc.jokeapplication.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsbc.jokeapplication.data.api.JokeRepository
import com.hsbc.jokeapplication.model.Joke
import kotlinx.coroutines.launch


class JokeViewModel(private val mainRepository: JokeRepository) : ViewModel() {
    private val _jokeLiveData = MutableLiveData<List<Joke>?>()
    private val _errorMessageLiveData = MutableLiveData<String>()

    fun getJokes(limit: Int) {
        viewModelScope.launch {
            val response = mainRepository.getJokes(limit)
            if (response.isSuccessful) {
                _jokeLiveData.value = response.body()
            } else {
                _errorMessageLiveData.value = response.code().toString()
            }

        }
    }

    fun getJokeLiveData(): MutableLiveData<List<Joke>?> {
        return _jokeLiveData
    }

    fun getJokeAPIErrorLiveData(): MutableLiveData<String> {
        return _errorMessageLiveData
    }
}