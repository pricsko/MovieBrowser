package com.mbh.moviebrowser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.mbh.moviebrowser.features.movieDetails.MovieDetailsFragment
import com.mbh.moviebrowser.features.movieDetailsPager.MovieDetailsPagerFragment

// TODO: Check if wifi or mobile data available
class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    }

    override fun onBackPressed() {
        val fragment = navHostFragment.childFragmentManager.fragments[0]
        if (fragment is MovieDetailsPagerFragment) {
            fragment.navigateBack()
        } else if (fragment is MovieDetailsFragment) {
            super.onBackPressed()
        }
    }
}
