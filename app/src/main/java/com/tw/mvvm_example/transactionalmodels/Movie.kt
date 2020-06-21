package com.tw.mvvm_example.transactionalmodels

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
      val id: Int,
      val poster_path: String,
      val overview: String,
      val release_date: String,
      val title: String,
      val original_title: String,
      val original_language: String
) : java.io.Serializable