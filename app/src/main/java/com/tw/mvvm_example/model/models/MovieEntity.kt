package com.tw.mvvm_example.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity constructor(
    @PrimaryKey var id: Int,
    var title: String,
    var overview: String,
    var poster_path: String,
    var release_date: String,
    var original_title: String,
    var original_language: String
)