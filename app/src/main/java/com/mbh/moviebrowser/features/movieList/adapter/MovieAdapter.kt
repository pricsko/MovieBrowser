package com.mbh.moviebrowser.features.movieList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigator
import androidx.recyclerview.widget.RecyclerView
import com.mbh.moviebrowser.databinding.ItemMovieBinding
import com.mbh.moviebrowser.domain.Movie

class MovieAdapter(
    private val items: List<Movie>,
    private val movieSelectionListener: (Movie, Navigator.Extras) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    private lateinit var binding: ItemMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, movieSelectionListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}