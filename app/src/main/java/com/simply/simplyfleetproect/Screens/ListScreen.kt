package com.simply.simplyfleetproect.Screens


import android.content.Context
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.simply.simplyfleetproect.Activities.ActivityMovieDetails

import com.simply.simplyfleetproect.Models.MovieSearchResult

import com.simply.simplyfleetproect.ViewModels.MovieListViewModel


@Composable
fun CardWithImageAndText(result: MovieSearchResult,onClick: () -> Unit) {

    val imagePainter = rememberImagePainter(data = result.Poster)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier.size(100.dp).
                weight(2f),
                contentScale = ContentScale.Crop,

            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp).weight(8f)
                    .align(Alignment.CenterVertically)
            ) {
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
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen(viewModel: MovieListViewModel, context: Context) {
    val list = viewModel.getAllMovies()
    var searchText by remember { mutableStateOf("") }

      val filteredList = if (searchText.isEmpty()) {
          list
      } else {
          list.filter { it.Title.contains(searchText, ignoreCase = true) }
      }

    Column {
        // Search bar
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search by title") }
        )

    LazyColumn {
        items(filteredList.size) { index ->
          //  val movie = list[index]
          val movie = filteredList[index]
            CardWithImageAndText(
                result = movie,
                onClick = {
                    Log.d("MyScreen", " clicked")
                    val intent = Intent(context, ActivityMovieDetails::class.java)
                    intent.putExtra("imdbID", movie.imdbID)
                    context.startActivity(intent)
                }
            )
        }
    }
} }

/*@Preview
@Composable
fun MyScreen() {
    CardWithImageAndText(
        imageResId = R.drawable.ic_launcher_background,
        title = "Title",
        year = "2005"
    )
}*/




