package com.mbh.moviebrowser.injection

import com.mbh.moviebrowser.MovieApplication.Companion.getApplicationModule
import com.mbh.moviebrowser.features.movieDetails.MovieDetailsFragment
import com.mbh.moviebrowser.features.movieDetails.di.DaggerMovieDetailsComponent
import com.mbh.moviebrowser.features.movieDetailsPager.MovieDetailsPagerFragment
import com.mbh.moviebrowser.features.movieList.MovieListFragment
import com.mbh.moviebrowser.features.movieList.di.DaggerMovieListComponent
import com.mbh.moviebrowser.features.movieListGrid.MovieListGridFragment

object InjectionManager {

    fun injectMovieListFragment(fragment: MovieListFragment) {
        fragment.activity?.let { activity ->
            val component = DaggerMovieListComponent.builder()
                .applicationModule(activity.getApplicationModule())
                .build()
            component.inject(fragment)
        }
    }

    fun injectMovieListGridFragment(fragment: MovieListGridFragment) {
        fragment.activity?.let { activity ->
            val component = DaggerMovieListComponent.builder()
                .applicationModule(activity.getApplicationModule())
                .build()
            component.inject(fragment)
        }
    }

    fun injectMovieDetailsPagerFragment(fragment: MovieDetailsPagerFragment) {
        fragment.activity?.let { activity ->
            val component = DaggerMovieListComponent.builder()
                .applicationModule(activity.getApplicationModule())
                .build()
            component.inject(fragment)
        }
    }

    fun injectMovieDetailsFragment(fragment: MovieDetailsFragment) {
        fragment.activity?.let { activity ->
            val component = DaggerMovieDetailsComponent.builder()
                .applicationModule(activity.getApplicationModule())
                .build()
            component.inject(fragment)
        }
    }

}