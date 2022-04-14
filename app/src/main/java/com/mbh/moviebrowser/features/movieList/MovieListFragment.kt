package com.mbh.moviebrowser.features.movieList

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.mbh.moviebrowser.databinding.FragmentMovieListBinding
import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.features.BaseFragment
import com.mbh.moviebrowser.features.movieList.adapter.MovieAdapter
import com.mbh.moviebrowser.injection.InjectionManager
import com.mbh.moviebrowser.util.SharedElementArgs.DEFAULT_SHARED_ELEMENTS_TRANSITION_TIME

class MovieListFragment : BaseFragment<MovieListViewModel>() {

    private lateinit var binding: FragmentMovieListBinding

    private var recyclerViewSaveState: Parcelable? = null

    override fun inject() {
        InjectionManager.injectMovieListFragment(this)
    }

    override fun setupViewModel(): MovieListViewModel {
        return getViewModel()
    }

    override fun observeViewModel() {
        viewModel.movies.observe(this) { movies ->
            initRecyclerView(movies)
        }

        viewModel.selectedMovie.observe(this) { (movie, sharedElements) ->
            val fragmentDirections = MovieListFragmentDirections.toMovieDetails(movie.id.toString())
            findNavController().navigate(fragmentDirections, sharedElements)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerViewSaveState = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSharedElementTransitions()
        viewModel.fetchMovies()
    }

    override fun onPause() {
        super.onPause()
        recyclerViewSaveState = binding.moviesRecyclerView.layoutManager?.onSaveInstanceState()
    }

    private fun initRecyclerView(movies: List<Movie>) {
        if (movies.isNotEmpty()) {
            val movieAdapter = MovieAdapter(movies, viewModel::movieSelected)
            binding.moviesRecyclerView.doOnPreDraw { startPostponedEnterTransition() }
            binding.moviesRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
                recyclerViewSaveState?.let {
                    layoutManager?.onRestoreInstanceState(it)
                }
            }
        } else {
            startPostponedEnterTransition()
            // TODO: empty screen with retry button
            // TODO: separate function, so it can be called immediately after no internet detected
        }
    }

    private fun setUpSharedElementTransitions() {
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
                .setDuration(DEFAULT_SHARED_ELEMENTS_TRANSITION_TIME)
        exitTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.no_transition)
        postponeEnterTransition()
    }

}
