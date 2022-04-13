package com.mbh.moviebrowser.interactor

import com.mbh.moviebrowser.data.MovieRepository
import com.mbh.moviebrowser.data.model.GenreDataModel
import com.mbh.moviebrowser.data.model.MovieDataModel
import com.mbh.moviebrowser.domain.Genre
import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.features.accessories.GenreProvider
import com.mbh.moviebrowser.network.ApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieInteractorImpl @Inject constructor(
    private val apiService: ApiService,
    private val repository: MovieRepository
) : MovieInteractor {

    override fun initGenres() {
        getGenreDataModelList()
            .onErrorReturn {
                repository.getAllGenres()
            }
            .subscribe { dataModelList ->
                GenreProvider.loadGenreMap(Genre.mapList(dataModelList))
            }
    }

    override fun getTrendingMovies(): Single<List<Movie>> {
        return getTrendingMoviesDataModelList()
            .onErrorReturn {
                repository.getAllMovies()
            }.map { dataModelList -> Movie.mapList(dataModelList) }
    }

    override fun getMovieById(movieId: Long): Single<Movie> {
        return getMovieDataModelById(movieId)
            .onErrorReturn {
                repository.getMovieById(movieId)
                    ?: throw IllegalArgumentException("movie not found: $movieId")
            }.map { dataModel -> Movie.map(dataModel) }
    }

    private fun getGenreDataModelList(): Single<List<GenreDataModel>> {
        return apiService.getGenres()
            .map { genreListResult ->
                val dataModelList = GenreDataModel.mapList(genreListResult.genres)
                repository.insertGenres(dataModelList)
                dataModelList
            }
    }

    private fun getTrendingMoviesDataModelList(): Single<List<MovieDataModel>> {
        return apiService.getTrendingMovies()
            .map { apiModelListResult ->
                val dataModelList = MovieDataModel.mapList(apiModelListResult.results)
                repository.insertMovies(dataModelList)
                dataModelList
            }
    }

    private fun getMovieDataModelById(movieId: Long): Single<MovieDataModel> {
        return apiService.getMovieById(movieId)
            .map { apiModel -> MovieDataModel.map(apiModel) }
    }


}