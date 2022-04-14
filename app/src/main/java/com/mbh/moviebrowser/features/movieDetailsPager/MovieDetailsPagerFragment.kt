package com.mbh.moviebrowser.features.movieDetailsPager

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
import com.mbh.moviebrowser.features.SharedElementFragment
import com.mbh.moviebrowser.features.movieDetailsPager.adapter.MovieDetailsAdapter
import com.mbh.moviebrowser.features.movieDetailsPager.adapter.MovieDetailsViewHolder
import com.mbh.moviebrowser.features.movieList.MovieListViewModel
import com.mbh.moviebrowser.injection.InjectionManager
import com.mbh.moviebrowser.util.AnimationUtils
import com.mbh.moviebrowser.util.addListeners
import com.mbh.moviebrowser.util.setNavigationResult

class MovieDetailsPagerFragment : SharedElementFragment<MovieListViewModel>() {

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

    override fun setUpBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentMovieDetailsPagerBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun setUpSharedElementTransitions() {
        val transition: Transition = TransitionInflater.from(context)
            .inflateTransition(R.transition.image_shared_element_transition)
        transition.addListeners(onStart = { _ ->
            val viewHolder =
                (binding.moviesPager[0] as RecyclerView).findViewHolderForAdapterPosition(
                    navArgs.index
                ) as? MovieDetailsViewHolder
            viewHolder?.let {
                AnimationUtils.getAlphaAnimatorOfViews(
                    arrayOf(
                        it.binding.description,
                        it.binding.title,
                        it.binding.gradient
                    ), 1000
                ).start()
            }
        })

        sharedElementEnterTransition = transition
        exitTransition =
            TransitionInflater.from(context)
                .inflateTransition(R.transition.grid_exit_transition)

        postponeEnterTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMovies()
    }

    override fun onPause() {
        super.onPause()
        setNavigationResult(binding.moviesPager.currentItem.toString())
    }

    fun navigateBack() {
        val viewHolder =
            (binding.moviesPager[0] as RecyclerView).findViewHolderForAdapterPosition(
                binding.moviesPager.currentItem
            ) as? MovieDetailsViewHolder ?: return
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
                offscreenPageLimit = 3
                orientation = ORIENTATION_HORIZONTAL
                adapter = movieDetailsAdapter
                setCurrentItem(navArgs.index, false)
            }
        } else {
            startPostponedEnterTransition()
        }
    }


}