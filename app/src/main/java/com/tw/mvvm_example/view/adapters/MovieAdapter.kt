package com.tw.mvvm_example.view.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tw.mvvm_example.R
import com.tw.mvvm_example.constants.Constants.Companion.MOVIE_SELECTED
import com.tw.mvvm_example.transactionalmodels.Movie
import kotlinx.android.synthetic.main.layout_movie.view.*

class MovieAdapter(
    private val items: List<Movie>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) =
            with(itemView) {
                tv_title.text = movie.title
                tv_originalTitle.text = movie.original_title
                tv_releaseDate.text = movie.release_date

                val movieBundle = Bundle().apply {
                    putSerializable(MOVIE_SELECTED, movie)
                }
                itemView.setOnClickListener {
                    println("redirecting to the details")
                    itemView.findNavController()
                        .navigate(
                            R.id.movieDetailFragment,
                            movieBundle,
                            navOptions,
                            null
                        )
                }
            }
    }

    val navOptions = NavOptions.Builder().setEnterAnim(R.anim.slide_in_right)
        .setPopEnterAnim(R.anim.slide_in_left).setExitAnim(R.anim.slide_out_left)
        .setPopExitAnim(R.anim.slide_out_right).build()
}
