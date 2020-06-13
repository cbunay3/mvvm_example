package com.tw.mvvm_example.model.dbservices

import com.tw.mvvm_example.model.models.MovieEntity
import com.tw.mvvm_example.model.repositories.MovieRepository
import org.jetbrains.anko.doAsync


class MovieDbService(
    private val movieRepository: MovieRepository
) {

    fun saveMovie(categories: List<MovieEntity>, callback: (result: String) -> Unit) {

        doAsync {
           //TODO repositorio insert
        }
    }
}
