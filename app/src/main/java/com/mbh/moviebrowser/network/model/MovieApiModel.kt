package com.mbh.moviebrowser.network.model

import com.google.gson.annotations.SerializedName

data class MovieApiModel(
    val id: Long,
    val title: String,
    @SerializedName("overview") val description: String,
    @SerializedName("genre_ids") val genreIds: List<Long>,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("poster_path") val coverUrl: String
)