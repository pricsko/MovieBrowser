package com.mbh.moviebrowser.features.movieDetailsPager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbh.moviebrowser.databinding.ItemMoviePagerBinding
import com.mbh.moviebrowser.domain.Movie

class MovieDetailsAdapter(
    private val items: List<Movie>,
    private val onImageReadyListener: () -> Unit
) :
    RecyclerView.Adapter<MovieDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailsViewHolder {
        val binding =
            ItemMoviePagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieDetailsViewHolder(binding, onImageReadyListener)
    }

    override fun onBindViewHolder(holder: MovieDetailsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}