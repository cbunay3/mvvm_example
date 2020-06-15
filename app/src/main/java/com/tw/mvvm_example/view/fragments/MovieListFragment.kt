package com.tw.mvvm_example.view.fragments

import androidx.fragment.app.Fragment
import com.tw.mvvm_example.transactionalmodels.Movie
import com.tw.mvvm_example.view.adapters.MovieAdapter
import kotlinx.android.synthetic.main.layout_movies.*

class MovieListFragment : Fragment() {

    private fun populateMovies(movies: List<Movie>) {
        rv_movies.adapter = MovieAdapter(movies)
    }
}