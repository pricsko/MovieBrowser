package com.mbh.moviebrowser.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mbh.moviebrowser.network.model.MovieApiModel
import com.mbh.moviebrowser.network.model.MovieResult

@Entity
data class MovieDataModel(
    @PrimaryKey val id: Long,
    val title: String,
    val overview: String,
    val rating: Float,
    val genreIds: List<Long>,
    val coverURL: String
) {
    companion object {
        fun map(apiModel: MovieApiModel): MovieDataModel {
            return MovieDataModel(
                apiModel.id,
                apiModel.title,
                apiModel.description,
                apiModel.rating,
                apiModel.genreIds,
                apiModel.coverUrl
            )
        }

        fun map(result: MovieResult): MovieDataModel {
            return MovieDataModel(
                result.id,
                result.title,
                result.description,
                result.rating,
                result.genres.map { it.id },
                result.coverUrl
            )
        }

        fun mapList(apiModelList: List<MovieApiModel>): List<MovieDataModel> {
            return apiModelList.map { map(it) }
        }
    }
}