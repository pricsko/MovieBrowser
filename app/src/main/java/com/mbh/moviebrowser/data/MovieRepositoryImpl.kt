package com.mbh.moviebrowser.data

import com.mbh.moviebrowser.MovieApplication
import com.mbh.moviebrowser.data.model.GenreDataModel
import com.mbh.moviebrowser.data.model.MovieDataModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(application: MovieApplication) : MovieRepository {

    private val movieDataBase: MovieDataBase = MovieDataBase.getInstance(application)

    override fun getAllMovies(): List<MovieDataModel> {
        return movieDataBase.movieDao().getAllMovies()
    }

    override fun getMovieById(movieId: Long): MovieDataModel? {
        return movieDataBase.movieDao().getMovieById(movieId)
    }

    override fun getAllGenres(): List<GenreDataModel> {
        return movieDataBase.movieDao().getAllGenres()
    }

    override fun insertMovies(movies: List<MovieDataModel>) {
        movieDataBase.movieDao().insertAllMovies(movies)
    }

    override fun insertGenres(genres: List<GenreDataModel>) {
        movieDataBase.movieDao().insertAllGenres(genres)
    }

    override fun clear() {
        movieDataBase.clearAllTables()
    }
}