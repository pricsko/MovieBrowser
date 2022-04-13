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
        val builder = StringBuilder()

        genreIdList.forEachIndexed { index, genreId ->
            val genreString = genreMap[genreId]
            genreString?.let {
                if (index > 0) builder.append(",")
                builder.append(it)
            }
        }
        return builder.toString()
    }

}