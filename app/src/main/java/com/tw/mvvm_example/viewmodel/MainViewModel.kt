package com.tw.mvvm_example.viewmodel

import androidx.lifecycle.*
import com.tw.mvvm_example.model.services.MovieService
import com.tw.mvvm_example.transactionalmodels.Movie
import com.tw.mvvm_example.transactionalmodels.MoviesWrapper
import me.linshen.retrofit2.adapter.ApiErrorResponse
import me.linshen.retrofit2.adapter.ApiSuccessResponse
import retrofit2.Call

class MainViewModel(
    private val movieService: MovieService
) : ViewModel() {
    var movies: MutableLiveData<List<Movie>> = MutableLiveData()
    init {
        movies.value = listOf()
    }

    fun getMovies(owner: LifecycleOwner) {
        movieService.getMoviesService().observe(owner,
            Observer { response ->
                when (response) {
                    is ApiSuccessResponse -> {
                        movies.value = listOf()
                        movies.value = response.body.results
                    }
                    is ApiErrorResponse -> {
                        println("Unable to get api response")
                    }
                }
            })
    }

}