package com.mbh.moviebrowser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.mbh.moviebrowser.data.MovieRepository
import com.mbh.moviebrowser.features.movieDetails.MovieDetailsFragment
import com.mbh.moviebrowser.features.movieDetailsPager.MovieDetailsPagerFragment
import com.mbh.moviebrowser.injection.InjectionManager
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

// TODO: Check if wifi or mobile data available
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: MovieRepository

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        InjectionManager.injectMainActivity(this)
    }

    override fun onBackPressed() {
        val fragment = navHostFragment.childFragmentManager.fragments[0]
        if (fragment is MovieDetailsPagerFragment) {
            fragment.navigateBack()
        } else if (fragment is MovieDetailsFragment) {
            super.onBackPressed()
        }
    }

    override fun onStop() {
        Single.fromCallable { repository.clear() }
            .subscribeOn(Schedulers.io())
            .subscribe { _ -> }
        super.onStop()
    }
}
