package com.tw.mvvm_example.transactionalmodels

import kotlinx.serialization.Serializable

@Serializable
data class MoviesWrapper(val results: List<Movie>):java.io.Serializable
