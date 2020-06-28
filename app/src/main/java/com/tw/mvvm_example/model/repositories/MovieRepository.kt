package com.tw.mvvm_example.model.repositories

import androidx.lifecycle.LiveData
import com.tw.mvvm_example.model.daos.MovieDao
import com.tw.mvvm_example.model.dtos.MovieDto
import com.tw.mvvm_example.model.mappers.MovieMapper
import com.tw.mvvm_example.model.models.MovieEntity

class MovieRepository(private val movieDao: MovieDao, private val movieMapper: MovieMapper) {

    fun save(movieDtos: List<MovieDto>): List<Long> {
        var moviesEntity = movieMapper.mapToEntity(movieDtos)
        return movieDao.insert(moviesEntity)
    }

    fun getAll(): LiveData<List<MovieEntity>> {
        return movieDao.getAll()
    }
}