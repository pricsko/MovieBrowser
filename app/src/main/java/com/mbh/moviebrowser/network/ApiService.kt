package com.mbh.moviebrowser.network

import com.mbh.moviebrowser.network.model.GenreListResult
import com.mbh.moviebrowser.network.model.MovieResult
import com.mbh.moviebrowser.network.model.MoviesListResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("genre/movie/list")
    fun getGenres(): Single<GenreListResult>

    @GET("trending/movie/week")
    fun getTrendingMovies(): Single<MoviesListResult>

    @GET("movie/{id}")
    fun getMovieById(@Path("id") id: Long): Single<MovieResult>

}