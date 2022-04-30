package com.mbh.moviebrowser.features.movieDetails

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.mbh.moviebrowser.databinding.FragmentMovieDetailsBinding
import com.mbh.moviebrowser.features.BaseFragment
import com.mbh.moviebrowser.injection.InjectionManager
import com.mbh.moviebrowser.util.SharedElementArgs.DEFAULT_SHARED_ELEMENTS_TRANSITION_TIME
import com.mbh.moviebrowser.util.addListeners

class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel>() {

    lateinit var binding: FragmentMovieDetailsBinding

    private val navArgs: MovieDetailsFragmentArgs by navArgs()

    override fun inject() {
        InjectionManager.injectMovieDetailsFragment(this)
    }

    override fun setupViewModel(): MovieDetailsViewModel {
        return getViewModel()
    }

    override fun observeViewModel() {
        viewModel.movie.observe(this) { movie ->
            binding.movie = movie
            binding.executePendingBindings()
            binding.cover.doOnPreDraw { startPostponedEnterTransition() }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSharedElementTransitions()
        try {
            viewModel.fetchMovie(navArgs.movieId.toLong())
        } catch (e: Exception) {
            throw UnsupportedOperationException("Invalid movie id: ${navArgs.movieId}")
        }
    }

    private fun setUpSharedElementTransitions() {
        val sharedElementEnter =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
                .setDuration(DEFAULT_SHARED_ELEMENTS_TRANSITION_TIME)
        sharedElementEnter.addListeners(onStart = {
            val animation = ObjectAnimator.ofFloat(binding.description, "alpha", 0f, 1f)
                .setDuration(DEFAULT_SHARED_ELEMENTS_TRANSITION_TIME * 2)
            animation.start()
        })
        sharedElementEnterTransition = sharedElementEnter
        enterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.no_transition)
        postponeEnterTransition()
    }

}
