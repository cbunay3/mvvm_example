package com.tw.mvvm_example.model.services

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import me.linshen.retrofit2.adapter.LiveDataCallAdapterFactory
import okhttp3.MediaType
import retrofit2.Retrofit

class RetrofitService {
   val contentType = MediaType.get("application/json")
    val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3")
            .addConverterFactory(Json.asConverterFactory(contentType))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()

    fun getRetrofitMovieService(): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}
