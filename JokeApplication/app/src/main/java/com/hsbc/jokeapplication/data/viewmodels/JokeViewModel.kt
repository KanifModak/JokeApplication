package com.hsbc.jokeapplication.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsbc.jokeapplication.data.api.JokeRepository
import com.hsbc.jokeapplication.model.Joke
import kotlinx.coroutines.launch


class JokeViewModel(private val mainRepository: JokeRepository) : ViewModel() {
    private val _jokeLiveData = MutableLiveData<List<Joke>?>()
    private val _errorMessageLiveData = MutableLiveData<String>()

    val jokeLiveData: LiveData<List<Joke>?> get() = _jokeLiveData
    val errorMessageLiveData: LiveData<String> get() = _errorMessageLiveData

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

}