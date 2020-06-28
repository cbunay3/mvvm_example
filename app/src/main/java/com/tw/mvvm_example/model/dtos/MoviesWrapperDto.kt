package com.tw.mvvm_example.model.dtos

import kotlinx.serialization.Serializable

@Serializable
data class MoviesWrapperDto(val results: List<MovieDto>) : java.io.Serializable