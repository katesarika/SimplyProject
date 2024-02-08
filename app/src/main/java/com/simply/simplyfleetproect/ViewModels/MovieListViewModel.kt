package com.simply.simplyfleetproect.ViewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.simplyfleetproect.Models.MovieSearchResult
import com.simply.simplyfleetproect.Other.OMDApi
import com.simply.simplyfleetproect.dao.MoviesListDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(val moviesListDao: MoviesListDao, val omdApi: OMDApi
): ViewModel() {

    val isDownload: LiveData<Boolean>
        get()=_isDownload
    private var _isDownload= MutableLiveData<Boolean>()


    fun getAllMovies() :List<MovieSearchResult>{
        return  moviesListDao.getAllMovies()
    }

    fun downloadMovies()//"searchKey:String,page:Int"
    {
        viewModelScope.launch {
            val response=  omdApi.searchMoviesFromList("fb112d5f","Batman",1);
            if (response.isSuccessful)
            {
 try{
                if (response.body()?.Response != null && response.body()?.Response == "True") {
                    for (i in response.body()?.Search!!)
                    {


                        if (!moviesListDao.isImdbIDAvail(i.imdbID))
                        {
                            val  movies= MovieSearchResult(i.imdbID,i.Title,i.Year,i.Type,
                                i.Poster )
                            moviesListDao.insertAll(movies)
                        }

                        _isDownload.postValue(true)

                    }
                }else{
                    _isDownload.postValue(false)
                }

            }catch (e:Exception)
 { e.printStackTrace()
            }
            }else{
                _isDownload.postValue(false)
            }
        }
    }
}