package com.mbh.moviebrowser.injection.app

import com.mbh.moviebrowser.MainActivity
import com.mbh.moviebrowser.injection.data.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

}