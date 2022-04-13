package com.mbh.moviebrowser.interactor

import com.mbh.moviebrowser.domain.Movie
import io.reactivex.rxjava3.core.Single

interface MovieInteractor {

    fun initGenres()

    fun getTrendingMovies(): Single<List<Movie>>

    fun getMovieById(movieId: Long): Single<Movie>

}