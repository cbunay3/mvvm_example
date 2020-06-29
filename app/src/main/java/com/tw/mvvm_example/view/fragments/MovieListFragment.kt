package com.tw.mvvm_example.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tw.mvvm_example.R
import com.tw.mvvm_example.view.adapters.MovieAdapter
import com.tw.mvvm_example.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.layout_movies.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isInternetAvailable = isInternetAvailable()
        viewModel.getMovies(this, isInternetAvailable)
        populateMovies()
    }

    private fun isInternetAvailable(): Boolean {
        val command = "ping -c 1 google.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }

    private fun populateMovies() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            rv_movies.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rv_movies.adapter = MovieAdapter(it)
        })
    }
}