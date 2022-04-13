package com.mbh.moviebrowser.injection.app

import com.mbh.moviebrowser.MovieApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MovieApplication) {

    @Provides
    @Singleton
    fun provideApplication(): MovieApplication {
        return application
    }

}