package com.mbh.moviebrowser.features.movieListGrid.adapeter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigator
import androidx.recyclerview.widget.RecyclerView
import com.mbh.moviebrowser.databinding.ItemMovieGridBinding
import com.mbh.moviebrowser.domain.Movie
import java.util.concurrent.atomic.AtomicInteger

class MovieGridAdapter(
    private val items: List<Movie>,
    private val selectedIndex: Int,
    private val movieSelectionListener: (Movie, Int, Navigator.Extras) -> Unit,
    private val onImageReadyListener: () -> Unit
) : RecyclerView.Adapter<MovieGridViewHolder>() {

    init {
        setHasStableIds(true)
    }

    companion object {
        private val gridWidth = AtomicInteger(0)

        fun getGridWidth(context: Context): Int {
            if (gridWidth.get() == 0) {
                gridWidth.compareAndSet(0, context.resources.displayMetrics.widthPixels / 2)
            }
            return gridWidth.get()
        }
    }

    private lateinit var binding: ItemMovieGridBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGridViewHolder {
        binding = ItemMovieGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieGridViewHolder(
            binding,
            selectedIndex,
            movieSelectionListener,
            onImageReadyListener,
            getGridWidth(parent.context)
        )
    }

    override fun onBindViewHolder(holder: MovieGridViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return items[position].id
    }

}