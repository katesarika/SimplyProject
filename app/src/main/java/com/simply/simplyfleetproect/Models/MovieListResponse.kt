package com.simply.simplyfleetproect.Models


data class MovieListResponse(
    val Search: List<MovieSearchResult>,
      val totalResults: Int,
     val Response: String)
