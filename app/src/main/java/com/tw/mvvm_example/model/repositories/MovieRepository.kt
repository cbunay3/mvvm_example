package com.tw.mvvm_example.model.repositories

import androidx.lifecycle.LiveData
import com.tw.mvvm_example.database.ApplicationDatabase
import com.tw.mvvm_example.model.daos.MovieDao
import com.tw.mvvm_example.model.dtos.MovieDto
import com.tw.mvvm_example.model.mappers.MovieMapper
import com.tw.mvvm_example.model.models.MovieEntity

class MovieRepository(dataBase: ApplicationDatabase, private val movieMapper: MovieMapper) {

    private val movieDao: MovieDao = dataBase.movieDao()

    fun save(moviesDto: List<MovieDto>): List<MovieEntity> {
        var moviesEntity = movieMapper.mapToEntity(moviesDto)
        movieDao.insert(moviesEntity)
        return moviesEntity
    }

    fun getAll(): LiveData<List<MovieEntity>> {
        return movieDao.getAll()
    }
}