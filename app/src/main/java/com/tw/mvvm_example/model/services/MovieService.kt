package com.tw.mvvm_example.model.services

import androidx.lifecycle.LiveData
import com.tw.mvvm_example.constants.Constants.Companion.KEY_API_MOVIE
import com.tw.mvvm_example.transactionalmodels.MoviesWrapper
import me.linshen.retrofit2.adapter.ApiResponse
import retrofit2.http.GET

interface MovieService {
    @GET("movie/upcoming?api_key=$KEY_API_MOVIE&language=es&page=1")
    fun getMoviesService(): LiveData<ApiResponse<MoviesWrapper>>
}