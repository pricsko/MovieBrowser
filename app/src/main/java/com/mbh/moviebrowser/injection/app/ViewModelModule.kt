package com.mbh.moviebrowser.injection.app

import androidx.lifecycle.ViewModelProvider
import com.mbh.moviebrowser.injection.accessories.DaggerAwareViewModelFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerAwareViewModelFactory): ViewModelProvider.Factory
}