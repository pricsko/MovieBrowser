package com.mbh.moviebrowser

import android.app.Activity
import android.app.Application
import com.mbh.moviebrowser.injection.app.ApplicationModule

class MovieApplication : Application() {

    companion object {
        fun Activity.getApplicationModule(): ApplicationModule {
            return (this.application as MovieApplication).applicationModule
        }
    }

    private lateinit var applicationModule: ApplicationModule

    override fun onCreate() {
        super.onCreate()

        applicationModule = ApplicationModule(this)
    }

}