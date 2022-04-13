package com.mbh.moviebrowser.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mbh.moviebrowser.data.model.GenreDataModel
import com.mbh.moviebrowser.data.model.MovieDataModel
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieDataModel")
    fun getAllMovies(): List<MovieDataModel>

    @Query("SELECT * FROM MovieDataModel WHERE id = :movieId")
    fun getMovieById(movieId: Long): MovieDataModel?

    @Query("SELECT * FROM GenreDataModel")
    fun getAllGenres(): List<GenreDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<MovieDataModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGenres(genres: List<GenreDataModel>)

    @Query("DELETE FROM GenreDataModel")
    fun deleteGenres()

    @Query("DELETE FROM MovieDataModel")
    fun deleteMovieDataModels()


}