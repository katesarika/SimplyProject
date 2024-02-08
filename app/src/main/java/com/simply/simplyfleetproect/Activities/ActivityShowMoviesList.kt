package com.simply.simplyfleetproect.Activities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.simply.simplyfleetproect.R
import com.simply.simplyfleetproect.Screens.MyScreen
import com.simply.simplyfleetproect.ViewModels.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityShowMoviesList : AppCompatActivity() {
    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_movies_list)
        movieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        if(isNetworkConnected(this)){
        movieListViewModel.downloadMovies()
        }

       /* movieListViewModel.isDownload().observe(this) { aBoolean ->
            if (aBoolean) {
                Toast.makeText(this, "Download Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Download Failed", Toast.LENGTH_SHORT).show()
            }
        }*/
        setContent {
            MyScreen(viewModel = movieListViewModel,context = this)
        }

    }
    companion object {
        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null
        }
    }
}