package com.mbh.moviebrowser.injection.data

import com.mbh.moviebrowser.data.MovieRepository
import com.mbh.moviebrowser.data.MovieRepositoryImpl
import com.mbh.moviebrowser.injection.app.ApplicationModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
internal abstract class DataModule {

    @Binds
    @Singleton
    internal abstract fun provideMovieRepository(repository: MovieRepositoryImpl): MovieRepository

}