package com.mbh.moviebrowser.network.model

import com.google.gson.annotations.SerializedName

data class MovieResult(
    val id: Long,
    val title: String,
    @SerializedName("overview") val description: String,
    @SerializedName("genres") val genres: List<GenreApiModel>,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("poster_path") val coverUrl: String
)