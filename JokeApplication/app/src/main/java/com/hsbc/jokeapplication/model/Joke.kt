package com.hsbc.jokeapplication.model

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("joke")
    val jokeMsg: String
)
