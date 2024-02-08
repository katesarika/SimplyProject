package com.simply.simplyfleetproect.Other

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//https://www.omdbapi.com/?apikey=fb112d5f&s=Batman&page=1
class RetrofitClient {


    companion object
    {
        @Volatile
        private var instance: Retrofit? = null


        fun getInstance(): Retrofit?{
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            instance= Retrofit.Builder().baseUrl("http://www.omdbapi.com").
            client(client)
                .addConverterFactory(GsonConverterFactory.create(gson)) // Add Gson converter

                .build()
            return instance
        }
    }

}