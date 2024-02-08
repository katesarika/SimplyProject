package com.simply.simplyfleetproect.Other

import android.content.Context
import com.simply.simplyfleetproect.dao.MoviesDetailsDao
import com.simply.simplyfleetproect.dao.MoviesListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MovieModules {

    @Provides
    fun  getApplication(@ApplicationContext context: Context): Context =context

    @Provides
    fun  getAppContext(@ApplicationContext context: Context): SimplyFleetDatabase = SimplyFleetDatabase.getInstance(context)


    @Provides
    fun  getRetrofitInstance(): OMDApi= RetrofitClient.getInstance()!!.create(OMDApi::class.java)



    @Provides
    fun  getMovieList(simplyFleetDatabase : SimplyFleetDatabase): MoviesListDao =simplyFleetDatabase.moviesListDao()

    @Provides
    fun  getDetailsOfMovies(simplyFleetDatabase : SimplyFleetDatabase): MoviesDetailsDao =simplyFleetDatabase.movieDetailsDao()
}