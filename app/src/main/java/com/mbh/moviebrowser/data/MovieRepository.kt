package com.mbh.moviebrowser.data

import com.mbh.moviebrowser.data.model.GenreDataModel
import com.mbh.moviebrowser.data.model.MovieDataModel

interface MovieRepository {

    fun getAllMovies(): List<MovieDataModel>

    fun getMovieById(movieId: Long): MovieDataModel?

    fun getAllGenres(): List<GenreDataModel>

    fun insertMovies(movies: List<MovieDataModel>)

    fun insertGenres(genres: List<GenreDataModel>)

    fun clear()

}