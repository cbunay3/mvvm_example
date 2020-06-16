package com.tw.mvvm_example.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.tw.mvvm_example.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI


class MovieDetailFragment : Fragment() {
    private var title = "Some title"
    private var originalTitle = "Original title"
    private var releaseDate = "yyyy-mm-dd"
    private var overview =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    private var posterPath = "url"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        drawingMovie(view)
    }

    private fun drawingMovie(view: View) {
        with(view as _LinearLayout)
        {
            scrollView {
                verticalLayout {
                    showMovieInformation("Título:", title)

                    showMovieInformation("Título original: ", originalTitle)
                    showMovieInformation("Fecha de lanzamiento:", releaseDate)
                    showMovieInformation("Overview:", overview)

                }.lparams(width = wrapContent, height = wrapContent)
            }.lparams(width = matchParent, height = matchParent)
        }
    }

    private fun @AnkoViewDslMarker _LinearLayout.showMovieInformation(
        header: String,
        headerValue: String
    ) {
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            textView {
                text = header
                textSizeDimen = R.dimen.text_size_content
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
            }.lparams(
                width = wrapContent, height = wrapContent
            )
            textView {
                text = headerValue
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
            }.lparams(
                width = wrapContent, height = wrapContent
            )
        }.lparams(width = wrapContent, height = wrapContent)
    }
}