package com.tw.mvvm_example.model.repositories

import androidx.lifecycle.LiveData
import com.tw.mvvm_example.database.ApplicationDatabase
import com.tw.mvvm_example.model.daos.MovieDao
import com.tw.mvvm_example.model.dtos.MovieDto
import com.tw.mvvm_example.model.mappers.MovieMapper
import com.tw.mvvm_example.model.models.MovieEntity

class MovieRepository(private val movieMapper: MovieMapper) {

    private lateinit var dataBase: ApplicationDatabase
    private val movieDao: MovieDao = dataBase.movieDao()

    fun save(movieDtos: List<MovieDto>): List<Long> {
        var moviesEntity = movieMapper.mapToEntity(movieDtos)
        return movieDao.insert(moviesEntity)
    }

    fun getAll(): LiveData<List<MovieEntity>> {
        return movieDao.getAll()
    }
}