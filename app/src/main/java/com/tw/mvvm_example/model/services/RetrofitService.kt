package com.tw.mvvm_example.model.services

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

class RetrofitService {
    private val contentType = MediaType.get("application/json")
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(Json.nonstrict.asConverterFactory(contentType))
        .build()

    fun getRetrofitMoviesService(): MovieService {
        return retrofit.create(MovieService::class.java)
    }

}
