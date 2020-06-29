package com.tw.mvvm_example.model.mappers

import com.tw.mvvm_example.model.dtos.MovieDto
import com.tw.mvvm_example.model.models.MovieEntity

class MovieMapper {

    fun mapToEntity(movieDtos: List<MovieDto>): List<MovieEntity> {
        val moviesEntity: MutableList<MovieEntity> = mutableListOf()
        movieDtos.map { moviesEntity.add(mapToEntity(it)) }
        return moviesEntity
    }

    fun mapToEntity(movieDto: MovieDto) = MovieEntity(
        id = movieDto.id,
        title = movieDto.title,
        overview = movieDto.overview,
        posterPath = movieDto.posterPath,
        releaseDate = movieDto.releaseDate,
        originalTitle = movieDto.originalTitle,
        originalLanguage = movieDto.originalLanguage
    )

    fun mapToDto(moviesEntity: List<MovieEntity>): List<MovieDto> {
        val movieDtos: MutableList<MovieDto> = mutableListOf()
        moviesEntity.map { movieDtos.add(mapToDto(it)) }
        return movieDtos
    }

    fun mapToDto(movieEntity: MovieEntity) = MovieDto(
        id = movieEntity.id,
        title = movieEntity.title,
        overview = movieEntity.overview,
        posterPath = movieEntity.posterPath,
        releaseDate = movieEntity.releaseDate,
        originalTitle = movieEntity.originalTitle,
        originalLanguage = movieEntity.originalLanguage
    )

}