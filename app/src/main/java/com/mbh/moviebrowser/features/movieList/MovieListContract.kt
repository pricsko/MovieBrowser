package com.mbh.moviebrowser.features.movieList

import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigator
import com.mbh.moviebrowser.domain.Movie

interface MovieListContract {

    interface ViewModel {
        val movies: MutableLiveData<List<Movie>>
        val selectedMovie: MutableLiveData<Pair<Movie, Navigator.Extras>>

        fun fetchMovies()
        fun movieSelected(movie: Movie, sharedElements: Navigator.Extras)
    }

}