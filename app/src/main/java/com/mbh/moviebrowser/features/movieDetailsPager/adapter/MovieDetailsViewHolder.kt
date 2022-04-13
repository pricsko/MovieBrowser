package com.mbh.moviebrowser.features.movieDetailsPager.adapter

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mbh.moviebrowser.databinding.ItemMoviePagerBinding
import com.mbh.moviebrowser.domain.Movie

class MovieDetailsViewHolder(
    val binding: ItemMoviePagerBinding,
    private val onImageReadyListener: () -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movie = movie

        // Need to delay parent fragment's enter transition until image loaded
        // because cover image is a shared element
        Glide.with(binding.cover)
            .load(movie.coverUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    onImageReadyListener.invoke()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    onImageReadyListener.invoke()
                    return false
                }

            })
            .into(binding.cover)

        binding.executePendingBindings()
    }
}