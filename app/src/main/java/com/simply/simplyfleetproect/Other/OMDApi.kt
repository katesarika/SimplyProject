package com.simply.simplyfleetproect.Other

import com.simply.simplyfleetproect.Models.MovieDetailsResponse
import com.simply.simplyfleetproect.Models.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDApi {
    @GET("http://www.omdbapi.com/")
    suspend fun searchMoviesFromList(
        @Query("apikey") apiKey: String,
        @Query("s") searchString: String,
        @Query("page") page: Int
    ): Response<MovieListResponse>


    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbID: String,
        @Query("apikey") apiKey: String
    ): Response<MovieDetailsResponse>
}