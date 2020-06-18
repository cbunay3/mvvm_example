package com.tw.mvvm_example.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tw.mvvm_example.R
import com.tw.mvvm_example.transactionalmodels.Movie
import com.tw.mvvm_example.transactionalmodels.MoviesWrapper
import com.tw.mvvm_example.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getMoviesService()
        getMovies()
    }

    private fun getMovies(){
        val call: Call<MoviesWrapper> = viewModel.getMoviesService()
        call.enqueue(object : Callback<MoviesWrapper?> {
            override fun onResponse(
                call: Call<MoviesWrapper?>?,
                response: Response<MoviesWrapper?>) {
                if (response.isSuccessful) {
                    viewModel.movies.addAll(response.body()?.results as ArrayList<Movie>)
                    val movies = viewModel.movies
                    movies.map {
                        movies_text.append(it.toString()+"\n\n")
                    }
                }
            }
            override  fun onFailure(call: Call<MoviesWrapper?>?, t: Throwable?) {
                Log.e(t.toString(), "Unable to get api response")
            }
        })
    }
}