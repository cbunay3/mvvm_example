package com.tw.mvvm_example.transactionalmodels

import kotlinx.serialization.Serializable
import com.tw.mvvm_example.transactionalmodels.Movie

@Serializable
data class MoviesWrapper(val movies: List<Movie>) :
    java.io.Serializable
