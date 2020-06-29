package com.tw.mvvm_example.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.tw.mvvm_example.model.dtos.MovieDto
import com.tw.mvvm_example.model.services.MovieService
import me.linshen.retrofit2.adapter.ApiErrorResponse
import me.linshen.retrofit2.adapter.ApiSuccessResponse

class MainViewModel(
    private val movieService: MovieService
) : ViewModel() {
    var movies: MutableLiveData<List<MovieDto>> = MutableLiveData()

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