package com.mbh.moviebrowser.features.movieListGrid.adapeter

import android.graphics.drawable.Drawable
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mbh.moviebrowser.databinding.ItemMovieGridBinding
import com.mbh.moviebrowser.domain.Movie

class MovieGridViewHolder(
    val binding: ItemMovieGridBinding,
    private val movieSelectionListener: (Movie, Int, Navigator.Extras) -> Unit,
    private val onImageReadyListener: () -> Unit,
    width: Int
) : RecyclerView.ViewHolder(binding.root) {

    init {
        val layoutParams = binding.root.layoutParams
        layoutParams.width = width
        layoutParams.height = (width / 2) * 3
        binding.root.layoutParams = layoutParams
    }

    fun bind(movie: Movie) {
        binding.movie = movie

        binding.root.setOnClickListener {
            val sharedElements = FragmentNavigator.Extras.Builder()
                .addSharedElements(
                    mapOf(
                        binding.cover to binding.cover.transitionName
                    )
                ).build()
            movieSelectionListener.invoke(movie, adapterPosition, sharedElements)
        }
        binding.simpleRatingBar.rating = movie.rating
        binding.simpleRatingBar.isEnabled = false
        binding.simpleRatingBar.isClickable = false

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