package com.mbh.moviebrowser.domain

import com.mbh.moviebrowser.BuildConfig
import com.mbh.moviebrowser.data.model.MovieDataModel
import com.mbh.moviebrowser.features.accessories.GenreProvider
import com.mbh.moviebrowser.util.toRatingFloat

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val coverUrl: String,
    val genres: String,
    val rating: Float,
    val ratingAsInt: Int
) {
    companion object {
        fun map(dataModel: MovieDataModel): Movie {
            return Movie(
                dataModel.id,
                dataModel.title,
                dataModel.overview,
                "${BuildConfig.IMAGE_BASE_URL}${dataModel.coverURL}",
                GenreProvider.mapGenres(dataModel.genreIds),
                dataModel.rating.toRatingFloat(10f, 5f, 0.5f),
                (dataModel.rating * 10).toInt()
            )
        }

        fun mapList(dataModeList: List<MovieDataModel>): List<Movie> {
            val movieList = ArrayList<Movie>()

            dataModeList.forEach { dataModel ->
                movieList.add(map(dataModel))
            }

            return movieList
        }
    }
}
