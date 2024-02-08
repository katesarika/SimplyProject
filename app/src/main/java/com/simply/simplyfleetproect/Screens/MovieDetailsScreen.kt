package com.simply.simplyfleetproect.Screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.simply.simplyfleetproect.Activities.ActivityMovieDetails
import com.simply.simplyfleetproect.Models.MovieDetailsResponse
import com.simply.simplyfleetproect.ViewModels.MovieDetailsVieModel
import com.simply.simplyfleetproect.ViewModels.MovieListViewModel


@Composable
fun CardWithImageDetails(result: MovieDetailsResponse) {

    val imagePainter = rememberImagePainter(data = result.Poster)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {


            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {

                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,

                    )
                Text(
                    text = result.Title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    maxLines = 2,

                    )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = result.Year,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    maxLines = 1,

                    )
                Text(
                    text = result.Actors,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    maxLines = 1,

                    )
            }
        }
    }


@Composable
fun MyScreen(viewModel: MovieDetailsVieModel) {
    val data = viewModel.getAllMoviesDetails()

            CardWithImageDetails(data)


}
