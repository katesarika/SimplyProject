package com.simply.simplyfleetproect.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.simplyfleetproect.Models.MovieDetailsResponse
import com.simply.simplyfleetproect.Models.MovieSearchResult
import com.simply.simplyfleetproect.Other.OMDApi
import com.simply.simplyfleetproect.dao.MoviesDetailsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MovieDetailsVieModel  @Inject constructor(val moviesDetailsDao: MoviesDetailsDao, val omdApi: OMDApi
): ViewModel() {
    lateinit var data :MovieDetailsResponse

    val isDownload: LiveData<Boolean>
        get()=_isDownload
    private var _isDownload= MutableLiveData<Boolean>()
   lateinit var imdIdd:String
    fun downloadMoviesDetails(imdId:String)
    {
        imdIdd=imdId
        viewModelScope.launch {
            val response=  omdApi.getMovieDetails(imdId,"fb112d5f");
            if (response.isSuccessful)
            {
try {


    if (response.body()?.Response != null && response.body()?.Response == "True") {


        if (!moviesDetailsDao.isImdbIDAvail(response.body()!!.imdbID)) {
            val movies = MovieDetailsResponse(
                response.body()!!.imdbID,
                response.body()!!.Title,
                response.body()!!.Year,
                response.body()!!.Plot,
                response.body()!!.Director,
                response.body()!!.Actors,
                response.body()!!.Poster,
                response.body()!!.Response,
            )

            // data=movies

            moviesDetailsDao.insertMovieDetails(movies)
        }

        _isDownload.postValue(true)


    } else {
        _isDownload.postValue(false)
    }
}catch (e: Exception)
{
    e.printStackTrace()
}
            }else{
                _isDownload.postValue(false)
            }
        }
    }

    fun getAllMoviesDetails() :MovieDetailsResponse{
        return  moviesDetailsDao.getMovieDetails(imdIdd)
    }

}