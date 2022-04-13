package com.mbh.moviebrowser.features.movieDetailsPager

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.mbh.moviebrowser.R
import com.mbh.moviebrowser.databinding.FragmentMovieDetailsPagerBinding
import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.features.BaseFragment
import com.mbh.moviebrowser.features.movieDetailsPager.adapter.MovieDetailsAdapter
import com.mbh.moviebrowser.features.movieDetailsPager.adapter.MovieDetailsViewHolder
import com.mbh.moviebrowser.features.movieList.MovieListViewModel
import com.mbh.moviebrowser.injection.InjectionManager
import com.mbh.moviebrowser.util.setNavigationResult

class MovieDetailsPagerFragment : BaseFragment<MovieListViewModel>() {

    private lateinit var binding: FragmentMovieDetailsPagerBinding
    private val navArgs: MovieDetailsPagerFragmentArgs by navArgs()

    override fun inject() {
        InjectionManager.injectMovieDetailsPagerFragment(this)
    }

    override fun setupViewModel(): MovieListViewModel {
        return getViewModel()
    }

    override fun observeViewModel() {
        viewModel.movies.observe(this) { movies ->
            initPager(movies)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsPagerBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSharedElementTransitions()
        activity?.let { viewModel.fetchMovies(it) }
    }

    override fun onPause() {
        super.onPause()
        setNavigationResult(binding.moviesPager.currentItem.toString())
    }

    fun navigateBack() {
        val viewHolder =
            (binding.moviesPager[0] as RecyclerView).findViewHolderForAdapterPosition(binding.moviesPager.currentItem) as? MovieDetailsViewHolder
                ?: return
        val sharedElements = FragmentNavigator.Extras.Builder()
            .addSharedElements(
                mapOf(
                    viewHolder.binding.cover to viewHolder.binding.cover.transitionName
                )
            ).build()
        findNavController().navigate(R.id.toMovieListGrid, null, null, sharedElements)
    }

    private fun initPager(movies: List<Movie>) {
        if (movies.isNotEmpty()) {
            val movieDetailsAdapter = MovieDetailsAdapter(movies) {
                startPostponedEnterTransition()
            }
            binding.moviesPager.apply {
                offscreenPageLimit = movies.size
                orientation = ORIENTATION_HORIZONTAL
                adapter = movieDetailsAdapter
                setCurrentItem(navArgs.index, false)
            }
        } else {
            startPostponedEnterTransition()
        }
    }

    private fun setUpSharedElementTransitions() {
        val transition: Transition = TransitionInflater.from(context)
            .inflateTransition(R.transition.image_shared_element_transition)
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {
                val viewHolder =
                    (binding.moviesPager[0] as RecyclerView).findViewHolderForAdapterPosition(
                        navArgs.index
                    ) as? MovieDetailsViewHolder
                        ?: return
                val animator =
                    ObjectAnimator.ofFloat(viewHolder.binding.description, "alpha", 0f, 1f)
                        .setDuration(1000)
                val animator2 =
                    ObjectAnimator.ofFloat(viewHolder.binding.title, "alpha", 0f, 1f)
                        .setDuration(1000)
                val animator3 =
                    ObjectAnimator.ofFloat(viewHolder.binding.gradient, "alpha", 0f, 1f)
                        .setDuration(1000)
                val animatorSet = AnimatorSet()
                animatorSet.playTogether(animator, animator2, animator3)
                animatorSet.start()
            }

            override fun onTransitionEnd(transition: Transition) {

            }

            override fun onTransitionCancel(transition: Transition) {

            }

            override fun onTransitionPause(transition: Transition) {

            }

            override fun onTransitionResume(transition: Transition) {

            }

        })

        sharedElementEnterTransition = transition
        exitTransition =
            TransitionInflater.from(context)
                .inflateTransition(R.transition.grid_exit_transition)

        postponeEnterTransition()
    }
}