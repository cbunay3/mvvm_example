package com.tw.mvvm_example.model.services

import com.tw.mvvm_example.transactionalmodels.MoviesWrapper
import retrofit2.Call
import com.tw.mvvm_example.constants.Constants.Companion.KEY_API_MOVIE
import retrofit2.http.GET

interface MovieService {
    @GET("movie/upcoming?api_key=$KEY_API_MOVIE&language=es&page=1")
    fun getMoviesService(): Call<MoviesWrapper>
}