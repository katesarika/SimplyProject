package com.simply.simplyfleetproect.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieSearchResult(
    @PrimaryKey val imdbID: String,
    val Title: String,
    val Year: String,
    val Type: String,
    val Poster: String
)
