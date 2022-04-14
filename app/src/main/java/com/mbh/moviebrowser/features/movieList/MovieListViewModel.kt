package com.mbh.moviebrowser.features.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigator
import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.interactor.MovieInteractor
import com.mbh.moviebrowser.util.InitDependentViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val interactor: MovieInteractor) : ViewModel(),
    MovieListContract.ViewModel, InitDependentViewModel {

    override val movies: MutableLiveData<List<Movie>> = MutableLiveData()
    override val selectedMovie: MutableLiveData<Pair<Movie, Navigator.Extras>> = MutableLiveData()

    override fun fetchMovies() {
        interactor.getTrendingMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { trendingMovies ->
                movies.value = trendingMovies
            }
    }

    override fun movieSelected(movie: Movie, sharedElements: Navigator.Extras) {
        this.selectedMovie.value = Pair(movie, sharedElements)
    }

    override fun initViewModel() {
        initGenres()
    }

    private fun initGenres() {
        interactor.initGenres()
    }

}
