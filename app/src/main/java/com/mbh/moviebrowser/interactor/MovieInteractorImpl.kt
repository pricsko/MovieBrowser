package com.mbh.moviebrowser.interactor

import com.mbh.moviebrowser.data.MovieRepository
import com.mbh.moviebrowser.data.model.GenreDataModel
import com.mbh.moviebrowser.data.model.MovieDataModel
import com.mbh.moviebrowser.domain.Genre
import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.features.accessories.GenreProvider
import com.mbh.moviebrowser.network.ApiService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MovieInteractorImpl @Inject constructor(
    private val apiService: ApiService,
    private val repository: MovieRepository
) : MovieInteractor {

    override fun initGenres() {
        Single.fromCallable { repository.getAllGenres() }
            .subscribeOn(Schedulers.io())
            .flatMap { genreList ->
                if (genreList.isEmpty()) {
                    apiService.getGenres()
                        .flatMap { apiModelList ->
                            val dataModelList = GenreDataModel.mapList(apiModelList.genres)
                            repository.insertGenres(dataModelList)
                            Single.just(repository.getAllGenres())
                        }
                } else {
                    Single.just(genreList)
                }
            }.subscribe { dataModelList ->
                GenreProvider.loadGenreMap(Genre.mapList(dataModelList))
            }
    }

    override fun getTrendingMovies(): Single<List<Movie>> {
        return Single.fromCallable { repository.getAllMovies() }
            .subscribeOn(Schedulers.io())
            .flatMap { movieDataModelList ->
                if (movieDataModelList.isEmpty()) {
                    apiService.getTrendingMovies()
                        .flatMap { apiModelList ->
                            val dataModelList = MovieDataModel.mapList(apiModelList.results)
                            repository.insertMovies(dataModelList)
                            Single.just(repository.getAllMovies())
                        }
                } else {
                    Single.just(movieDataModelList)
                }
            }.map { dataModelList ->
                Movie.mapList(dataModelList)
            }
    }

    override fun getMovieById(movieId: Long): Single<Movie> {
        return Single.fromCallable {
            repository.getMovieById(movieId)
                ?: apiService.getMovieById(movieId)
                    .map { movieResult ->
                        MovieDataModel.map(movieResult)
                    }.blockingGet()
        }
            .subscribeOn(Schedulers.io())
            .map { Movie.map(it) }
    }

    override fun clearDataBase() {
        Single.fromCallable { repository.clear() }
            .subscribeOn(Schedulers.io())
            .subscribe { _ -> }
    }


}