package com.mbh.moviebrowser.features.movieListGrid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import com.mbh.moviebrowser.R
import com.mbh.moviebrowser.databinding.FragmentMovieListGridBinding
import com.mbh.moviebrowser.domain.Movie
import com.mbh.moviebrowser.features.BaseFragment
import com.mbh.moviebrowser.features.movieList.MovieListViewModel
import com.mbh.moviebrowser.features.movieListGrid.adapeter.MovieGridAdapter
import com.mbh.moviebrowser.injection.InjectionManager
import com.mbh.moviebrowser.util.getNavigationResult
import kotlin.math.max


class MovieListGridFragment : BaseFragment<MovieListViewModel>() {

    companion object {
        private var selectedIndex = 0
    }

    private lateinit var binding: FragmentMovieListGridBinding

    override fun inject() {
        InjectionManager.injectMovieListGridFragment(this)
    }

    override fun setupViewModel(): MovieListViewModel {
        return getViewModel()
    }

    override fun observeViewModel() {
        viewModel.movies.observe(this) { movies ->
            initRecyclerView(movies)
        }

        viewModel.selectedMovie.observe(this) { (movie, sharedElements) ->
            val fragmentDirections =
                MovieListGridFragmentDirections.toMovieDetailsPager(
                    movie.id.toString(),
                    selectedIndex
                )
            findNavController().navigate(fragmentDirections, sharedElements)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListGridBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setUpSharedElementTransitions()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { viewModel.fetchMovies(it) }
    }

    private fun initRecyclerView(movies: List<Movie>) {
        if (movies.isNotEmpty()) {
            // TODO: preload elements for smoother animation between grid and pager
            val returnPositionResult = getNavigationResult()
            returnPositionResult?.value?.let { returnPositionString ->
                val returnPosition = returnPositionString.toInt()
                selectedIndex = returnPosition
            }

            val movieSelectionListener: (Movie, Int, Navigator.Extras) -> Unit =
                { movie, selectedMovieIndex, sharedElements ->
                    selectedIndex = selectedMovieIndex
                    viewModel.movieSelected(movie, sharedElements)
                }
            val onImageReadyListener: () -> Unit = {
                startPostponedEnterTransition()
            }
            val movieAdapter =
                MovieGridAdapter(movies, movieSelectionListener, onImageReadyListener)

            binding.moviesRecyclerView.apply {
                setHasFixedSize(true)
                setItemViewCacheSize(movies.size)
                layoutManager = GridLayoutManager(context, 2)
                adapter = movieAdapter
                // Scroll to (position - 2) so the selected item can be in the middle
                binding.moviesRecyclerView.scrollToPosition(max(selectedIndex - 2, 0))
            }
        } else {
            startPostponedEnterTransition()
            // TODO: empty screen with retry button
            // TODO: separate function, so it can be called immediately after no internet detected
        }
    }

    private fun setUpSharedElementTransitions() {
        exitTransition =
            TransitionInflater.from(context)
                .inflateTransition(R.transition.grid_exit_transition)
        val transition: Transition = TransitionInflater.from(context)
            .inflateTransition(R.transition.image_shared_element_transition)
        sharedElementEnterTransition = transition
        postponeEnterTransition()
    }
}
