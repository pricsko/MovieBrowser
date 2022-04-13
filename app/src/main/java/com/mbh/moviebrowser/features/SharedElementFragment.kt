package com.mbh.moviebrowser.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel

abstract class SharedElementFragment<T : ViewModel> : BaseFragment<T>() {

    abstract fun setUpBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View

    abstract fun setUpSharedElementTransitions()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = setUpBinding(inflater, container)
        setUpSharedElementTransitions()
        return view
    }

}