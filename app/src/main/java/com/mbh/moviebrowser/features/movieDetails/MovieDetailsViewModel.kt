package com.mbh.moviebrowser.features.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.interactor.MovieInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(private val interactor: MovieInteractor) :
    ViewModel(), MovieDetailsContract.ViewModel {

    override var movie = MutableLiveData<Movie>()

    override fun fetchMovie(movieId: Long) {
        interactor.getMovieById(movieId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { movieById ->
                movie.value = movieById
            }
    }

}
