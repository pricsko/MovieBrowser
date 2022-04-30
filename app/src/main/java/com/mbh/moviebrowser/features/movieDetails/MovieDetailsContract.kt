package com.mbh.moviebrowser.features.movieDetails

import androidx.lifecycle.MutableLiveData
import com.mbh.moviebrowser.domain.Movie

interface MovieDetailsContract {

    interface ViewModel {
        var movie: MutableLiveData<Movie>

        fun fetchMovie(movieId: Long)
    }

}