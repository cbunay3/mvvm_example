package com.tw.mvvm_example.model.models
import kotlinx.serialization.Serializable

@Serializable
data class MovieEntity constructor(
    private val id: Int = 0,
    private val title: String,
    private val overview: String,
    private val poster_path: String
)