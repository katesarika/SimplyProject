package com.simply.simplyfleetproect.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.simply.simplyfleetproect.Activities.ActivityShowMoviesList.Companion.isNetworkConnected
import com.simply.simplyfleetproect.R
import com.simply.simplyfleetproect.Screens.MyScreen
import com.simply.simplyfleetproect.ViewModels.MovieDetailsVieModel
import com.simply.simplyfleetproect.ViewModels.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityMovieDetails : AppCompatActivity() {

    private lateinit var movieDetailsVieModel: MovieDetailsVieModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val imdbID = intent.getStringExtra("imdbID")

        movieDetailsVieModel = ViewModelProvider(this).get(MovieDetailsVieModel::class.java)
        if (imdbID != null) {
            if(isNetworkConnected(this)) {
                movieDetailsVieModel.downloadMoviesDetails(imdbID)
            }
        }
        setContent {
            MyScreen(viewModel = movieDetailsVieModel)
        }
    }
}