package com.tw.mvvm_example.model.mappers

import com.tw.mvvm_example.model.dtos.MovieDto
import com.tw.mvvm_example.model.models.MovieEntity

class MovieMapper {

    fun mapToEntity(moviesDto: List<MovieDto>): List<MovieEntity> {
        val moviesEntity: MutableList<MovieEntity> = mutableListOf()
        moviesDto.map { moviesEntity.add(mapToEntity(it)) }
        return moviesEntity
    }

    fun mapToEntity(movieDto: MovieDto) = MovieEntity(
        id = movieDto.id,
        title = movieDto.title,
        overview = movieDto.overview,
        poster_path = movieDto.poster_path,
        release_date = movieDto.release_date,
        original_title = movieDto.original_title,
        original_language = movieDto.original_language
    )

    fun mapToDto(moviesEntity: List<MovieEntity>): List<MovieDto> {
        val moviesDto: MutableList<MovieDto> = mutableListOf()
        moviesEntity.map { moviesDto.add(mapToDto(it)) }
        return moviesDto
    }

    fun mapToDto(movieEntity: MovieEntity) = MovieDto(
        id = movieEntity.id,
        title = movieEntity.title,
        overview = movieEntity.overview,
        poster_path = movieEntity.poster_path,
        release_date = movieEntity.release_date,
        original_title = movieEntity.original_title,
        original_language = movieEntity.original_language
    )

}