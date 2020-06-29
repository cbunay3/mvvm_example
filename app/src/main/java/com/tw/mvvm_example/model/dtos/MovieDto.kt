package com.tw.mvvm_example.model.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto constructor(
    @SerialName("id") val id: Int,
    @SerialName("poster_path") val posterPath: String,
    val overview: String,
    @SerialName("release_date") val releaseDate: String,
    val title: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("original_language") val originalLanguage: String
) : java.io.Serializable