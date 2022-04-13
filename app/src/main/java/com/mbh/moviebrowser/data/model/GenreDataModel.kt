package com.mbh.moviebrowser.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mbh.moviebrowser.network.model.GenreApiModel

@Entity
data class GenreDataModel(@PrimaryKey val id: Long, val name: String) {
    companion object {
        fun map(apiModel: GenreApiModel): GenreDataModel {
            return GenreDataModel(
                apiModel.id,
                apiModel.name
            )
        }

        fun mapList(apiModelList: List<GenreApiModel>): List<GenreDataModel> {
            return apiModelList.map { map(it) }
        }
    }
}