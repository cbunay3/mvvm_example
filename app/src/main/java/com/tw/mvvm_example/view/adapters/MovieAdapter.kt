package com.tw.mvvm_example.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tw.mvvm_example.R
import com.tw.mvvm_example.transactionalmodels.Movie
import kotlinx.android.synthetic.main.layout_movie.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

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
                tv_overview.text = movie.overview
                itemView.onClick {
                    println("redirecting to the details")
                }
            }
    }
}
