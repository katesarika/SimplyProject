package com.simply.simplyfleetproect.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simply.simplyfleetproect.Models.MovieSearchResult

@Dao
interface MoviesListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: MovieSearchResult)

    @Query("SELECT EXISTS(SELECT * FROM MovieSearchResult where imdbID=:imdbID)")
    fun isImdbIDAvail(imdbID: String): Boolean

    @Query("SELECT * FROM MovieSearchResult")
    fun getAllMovies(): List<MovieSearchResult>
}