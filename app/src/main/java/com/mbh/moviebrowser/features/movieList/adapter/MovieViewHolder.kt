package com.mbh.moviebrowser.features.movieList.adapter

import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import com.mbh.moviebrowser.databinding.ItemMovieBinding
import com.mbh.moviebrowser.domain.Movie

class MovieViewHolder(
    val binding: ItemMovieBinding,
    private val movieSelectionListener: (Movie, Navigator.Extras) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movie = movie

        binding.root.setOnClickListener {
            val sharedElements = FragmentNavigator.Extras.Builder()
                .addSharedElements(
                    mapOf(
                        binding.cover to binding.cover.transitionName,
                        binding.title to binding.title.transitionName
                    )
                ).build()
            movieSelectionListener.invoke(movie, sharedElements)
        }

        binding.executePendingBindings()
    }

}