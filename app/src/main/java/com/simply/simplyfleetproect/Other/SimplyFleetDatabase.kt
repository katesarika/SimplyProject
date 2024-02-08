package com.simply.simplyfleetproect.Other

import android.content.Context
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase
import com.simply.simplyfleetproect.Models.MovieDetailsResponse
import com.simply.simplyfleetproect.Models.MovieListResponse
import com.simply.simplyfleetproect.Models.MovieSearchResult
import com.simply.simplyfleetproect.dao.MoviesDetailsDao
import com.simply.simplyfleetproect.dao.MoviesListDao
import java.util.concurrent.Executors

@Database(entities = [MovieSearchResult::class,MovieDetailsResponse::class], version = 1, exportSchema = false)
abstract class SimplyFleetDatabase : RoomDatabase() {

    abstract fun movieDetailsDao(): MoviesDetailsDao
    abstract fun moviesListDao(): MoviesListDao

    companion object {
        @Volatile
        private var INSTANCE: SimplyFleetDatabase? = null

        fun getInstance(context: Context): SimplyFleetDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SimplyFleetDatabase::class.java,
                    "SimplyFleetDatabase"
                )  .setQueryCallback(queryCallback = object :
                    QueryCallback {
                    override fun onQuery(sqlQuery: String, bindArgs: List<Any?>) {
                        println("SQL Query: $sqlQuery SQL Args: $bindArgs")

                    }
                }, Executors.newSingleThreadExecutor()).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}



