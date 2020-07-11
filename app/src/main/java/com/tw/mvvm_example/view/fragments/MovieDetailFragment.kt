package com.tw.mvvm_example.view.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.Composable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.ui.foundation.Text
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.bumptech.glide.Glide
import com.tw.mvvm_example.R
import com.tw.mvvm_example.constants.Constants.Companion.MOVIE_SELECTED
import com.tw.mvvm_example.transactionalmodels.Movie
import com.tw.mvvm_example.view.activities.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI


class MovieDetailFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var movie: Movie
    private lateinit var title: String
    private lateinit var original_title: String
    private lateinit var release_date: String
    private lateinit var overview: String
    private lateinit var poster_path: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        movie = bundle?.get(MOVIE_SELECTED) as Movie
        title = movie.title
        original_title = movie.original_title
        release_date = movie.release_date
        overview = movie.overview
        poster_path = movie.poster_path

    }

    @Composable
    fun text(){
        Text(text= "testing")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = this.activity as MainActivity
        return createMainLayout()
    }

    private fun createMainLayout(
    ): View {
        return UI {
            verticalLayout {
                backgroundColor =
                    ContextCompat.getColor(context, R.color.colorWhite)
                lparams(width = matchParent, height = matchParent)
            }
        }.view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.activeButtonBack()
        //text()
        drawingMovie(view)
    }

    private fun drawingMovie(view: View) {
        with(view as _LinearLayout)
        {
            scrollView {
                verticalLayout {
                    showMovieInformation(R.string.movie_title, title)
                    loadMovieImage(view)
                    showMovieInformation(R.string.original_title, original_title)
                    showMovieInformation(R.string.release_date, release_date)
                    showMovieInformation(R.string.overview, overview)

                }.lparams(width = wrapContent, height = wrapContent) {

                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }

    private fun @AnkoViewDslMarker _LinearLayout.loadMovieImage(
        view: View
    ) {
        imageView {
            val imageUrl = context.getString(R.string.image_url)+ poster_path
            Glide.with(view)
                .load(imageUrl).fitCenter()
                .override(500, 1000)
                .error(R.drawable.movie)
                .into(this)
        }.lparams()
        {
            rightMargin = dip(15)
            leftMargin = dip(15)
            gravity = Gravity.CENTER_HORIZONTAL
        }
    }

    private fun @AnkoViewDslMarker _LinearLayout.showMovieInformation(
        idText: Int,
        headerValue: String
    ) {
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            textView {
                text = context.getString(idText)
                textSizeDimen = R.dimen.text_size_content
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                setTypeface(typeface, Typeface.BOLD)
            }.lparams(
                width = wrapContent, height = wrapContent
            )
            textView {
                text = headerValue
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                textSizeDimen = R.dimen.text_size_body
            }.lparams(
                width = wrapContent, height = wrapContent
            )
        }.lparams(width = wrapContent, height = wrapContent) {
            topMargin = dip(10)
            leftMargin = dip(10)
            bottomMargin = dip(10)
        }
    }
}