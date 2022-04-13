package com.mbh.moviebrowser.domain

import com.mbh.moviebrowser.data.model.GenreDataModel

data class Genre(val id: Long, val name: String) {

    companion object {
        fun map(dataModel: GenreDataModel): Genre {
            return Genre(dataModel.id, dataModel.name)
        }

        fun mapList(dataModeList: List<GenreDataModel>): List<Genre> {
            val genreList = ArrayList<Genre>()

            dataModeList.forEach { dataModel ->
                genreList.add(map(dataModel))
            }

            return genreList
        }
    }
}