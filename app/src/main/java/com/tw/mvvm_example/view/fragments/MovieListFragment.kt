package com.tw.mvvm_example.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tw.mvvm_example.R
import com.tw.mvvm_example.transactionalmodels.Movie
import com.tw.mvvm_example.transactionalmodels.MoviesWrapper
import com.tw.mvvm_example.view.activities.MainActivity
import com.tw.mvvm_example.view.adapters.MovieAdapter
import com.tw.mvvm_example.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.layout_movies.*
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = this.activity as MainActivity
        return inflater.inflate(R.layout.layout_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovies()
    }

    private fun getMovies() {
        val call: Call<MoviesWrapper> = viewModel.getMoviesService()
        call.enqueue(object : Callback<MoviesWrapper?> {
            override fun onResponse(
                call: Call<MoviesWrapper?>?,
                response: Response<MoviesWrapper?>
            ) {
                if (response.isSuccessful) {
                    viewModel.movies.addAll(response.body()?.results as ArrayList<Movie>)
                    populateMovies(viewModel.movies)
                }
            }

            override fun onFailure(call: Call<MoviesWrapper?>?, t: Throwable?) {
                Log.e(t.toString(), "Unable to get api response")
            }
        })
    }

    private fun populateMovies(movies: List<Movie>) {
        rv_movies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_movies.adapter = MovieAdapter(movies)
    }
}