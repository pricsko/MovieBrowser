package com.mbh.moviebrowser.features.accessories

import com.mbh.moviebrowser.domain.Genre
import java.util.*

object GenreProvider {

    private val genreMap = Collections.synchronizedMap(HashMap<Long, String>())

    fun loadGenreMap(genres: List<Genre>) {
        genres.forEach { genre ->
            genreMap.putIfAbsent(genre.id, genre.name)
        }
    }

    fun mapGenres(genreIdList: List<Long>): String {
        return when {
            genreIdList.isEmpty() -> ""
            else -> genreIdList.filter { genreMap[it] != null }.map { genreMap[it] }
                .joinToString(",")
        }
    }

}