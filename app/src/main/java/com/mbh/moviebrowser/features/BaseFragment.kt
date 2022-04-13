package com.mbh.moviebrowser.features

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mbh.moviebrowser.util.InitDependentViewModel
import javax.inject.Inject

abstract class BaseFragment<T : ViewModel> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    internal open lateinit var viewModel: T

    protected abstract fun inject()
    protected abstract fun setupViewModel(): T
    protected abstract fun observeViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject()
        viewModel = setupViewModel()
        if (viewModel is InitDependentViewModel) {
            (viewModel as InitDependentViewModel).initViewModel()
        }
        observeViewModel()
    }

    protected inline fun <reified T : ViewModel> getViewModel(): T {
        return ViewModelProvider(this, viewModelFactory)[T::class.java]
    }

}