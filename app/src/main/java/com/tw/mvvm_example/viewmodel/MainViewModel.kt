package com.tw.mvvm_example.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.tw.mvvm_example.model.dtos.MovieDto
import com.tw.mvvm_example.model.mappers.MovieMapper
import com.tw.mvvm_example.model.repositories.MovieRepository
import com.tw.mvvm_example.model.services.MovieService
import me.linshen.retrofit2.adapter.ApiErrorResponse
import me.linshen.retrofit2.adapter.ApiSuccessResponse
import org.jetbrains.anko.doAsync

class MainViewModel(
    private val movieService: MovieService,
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : ViewModel() {
    var movies: MutableLiveData<List<MovieDto>> = MutableLiveData()

    init {
        movies.value = listOf()
    }

    fun getMovies(owner: LifecycleOwner, isInternetAvailable: Boolean) {
        if (isInternetAvailable) {
            movieService.getMoviesService().observe(owner,
                Observer { response ->
                    when (response) {
                        is ApiSuccessResponse -> {
                            movies.value = listOf()
                            movies.value = response.body.results
                            doAsync {
                                movieRepository.save(response.body.results)
                            }
                        }
                        is ApiErrorResponse -> {
                            println("Unable to get api response")
                        }
                    }
                })
        } else {
            movieRepository.getAll().observe(owner,
                Observer {
                    movies.value = listOf()
                    movies.value = movieMapper.mapToDto(it)
                }
            )
        }

    }

}