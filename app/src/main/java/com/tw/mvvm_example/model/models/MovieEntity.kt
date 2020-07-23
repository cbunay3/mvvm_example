package com.tw.mvvm_example.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity constructor(
    @PrimaryKey var id: Int,
    var title: String?,
    var overview: String?,
    var posterPath: String?,
    var releaseDate: String?,
    var originalTitle: String?,
    var originalLanguage: String?
)