package com.tw.mvvm_example.transactionalmodels

import kotlinx.serialization.Serializable

@Serializable
data class Movie constructor(
    private val id: Int = 0,
    private val title: String,
    private val overview: String,
    private val poster_path: String
) : java.io.Serializable