package com.simply.simplyfleetproect.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.simply.simplyfleetproect.Models.MovieDetailsResponse
@Dao
interface MoviesDetailsDao {

    @Insert()
    suspend fun insertMovieDetails(movieDetails: MovieDetailsResponse)

    @Query("SELECT EXISTS(SELECT * FROM MovieDetailsResponse where imdbID=:imdbID)")
    fun isImdbIDAvail(imdbID: String): Boolean

    @Query("SELECT * FROM MovieDetailsResponse WHERE imdbID = :imdbID")
     fun getMovieDetails(imdbID: String): MovieDetailsResponse
}