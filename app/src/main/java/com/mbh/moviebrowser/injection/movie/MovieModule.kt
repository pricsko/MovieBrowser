package com.mbh.moviebrowser.injection.movie

import com.mbh.moviebrowser.injection.data.ApiModule
import com.mbh.moviebrowser.injection.data.DataModule
import com.mbh.moviebrowser.interactor.MovieInteractor
import com.mbh.moviebrowser.interactor.MovieInteractorImpl
import dagger.Binds
import dagger.Module

@Module(includes = [ApiModule::class, DataModule::class])
internal abstract class MovieModule {

    @Binds
    internal abstract fun bindMovieInteractor(interactor: MovieInteractorImpl): MovieInteractor

}