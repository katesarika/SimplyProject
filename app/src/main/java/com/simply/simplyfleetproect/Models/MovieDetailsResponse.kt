package com.simply.simplyfleetproect.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDetailsResponse(
    @PrimaryKey val imdbID: String,
    val Title: String,
    val Year: String,
    val Plot: String,
    val Director: String,
    val Actors: String,
    val Poster: String,
    val Response: String

)
