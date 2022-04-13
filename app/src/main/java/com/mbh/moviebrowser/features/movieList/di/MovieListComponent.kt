package com.mbh.moviebrowser.features.movieList.di

import com.mbh.moviebrowser.features.movieDetailsPager.MovieDetailsPagerFragment
import com.mbh.moviebrowser.features.movieList.MovieListFragment
import com.mbh.moviebrowser.features.movieListGrid.MovieListGridFragment
import com.mbh.moviebrowser.injection.app.ViewModelModule
import com.mbh.moviebrowser.injection.movie.MovieModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MovieModule::class, ViewModelModule::class, MovieListModule::class])
interface MovieListComponent {

    fun inject(fragment: MovieListFragment)

    fun inject(fragment: MovieListGridFragment)

    fun inject(fragment: MovieDetailsPagerFragment)
}