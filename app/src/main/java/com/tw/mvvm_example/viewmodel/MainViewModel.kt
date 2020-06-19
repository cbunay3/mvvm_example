package com.tw.mvvm_example.viewmodel

import androidx.lifecycle.ViewModel
import com.tw.mvvm_example.model.services.MovieService
import com.tw.mvvm_example.transactionalmodels.Movie
import com.tw.mvvm_example.transactionalmodels.MoviesWrapper
import retrofit2.Call

class MainViewModel(
    private val movieService: MovieService
) : ViewModel() {
    var movies: ArrayList<Movie> = arrayListOf()

    fun  getMoviesService(): Call<MoviesWrapper> {
        return movieService.getMoviesService()
    }

}