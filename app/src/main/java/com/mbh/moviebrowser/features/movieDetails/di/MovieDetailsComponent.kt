package com.mbh.moviebrowser.features.movieDetails.di

import com.mbh.moviebrowser.features.movieDetails.MovieDetailsFragment
import com.mbh.moviebrowser.injection.app.ViewModelModule
import com.mbh.moviebrowser.injection.movie.MovieModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MovieModule::class, ViewModelModule::class, MovieDetailsModule::class])
interface MovieDetailsComponent {

    fun inject(fragment: MovieDetailsFragment)
}