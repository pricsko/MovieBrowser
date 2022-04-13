package com.mbh.moviebrowser.features.movieDetails.di

import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.features.movieDetails.MovieDetailsViewModel
import com.mbh.moviebrowser.injection.accessories.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class MovieDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    internal abstract fun bindMovieDetailsViewModel(viewModel: MovieDetailsViewModel): ViewModel
}