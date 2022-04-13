package com.mbh.moviebrowser.features.movieList.di

import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.features.movieList.MovieListViewModel
import com.mbh.moviebrowser.injection.accessories.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class MovieListModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun bindMovieListViewModel(viewModel: MovieListViewModel): ViewModel
}