package com.tw.mvvm_example.model.services

import androidx.lifecycle.LiveData
import me.linshen.retrofit2.adapter.ApiResponse
import com.tw.mvvm_example.transactionalmodels.MoviesWrapper
import retrofit2.http.GET

interface MovieService {

    @GET("movie/upcoming?api_key=f4187b986305de51c5a82561625b0982")
    fun getMovies(): ApiResponse<MoviesWrapper>
}