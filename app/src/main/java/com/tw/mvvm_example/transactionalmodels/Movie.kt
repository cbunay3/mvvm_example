package com.tw.mvvm_example.transactionalmodels

import kotlinx.serialization.Serializable

@Serializable
data class Movie constructor(
    private val id: Int,
    private val poster_path: String,
    private val overview: String,
    private val release_date: String,
    private val title: String,
    private val original_title: String,
    private val original_language: String
) : java.io.Serializable