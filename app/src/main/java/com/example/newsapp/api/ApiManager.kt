package com.example.newsapp.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    const val apiKey = "43de6fb94bd34acf8d909ad86b2ca435"
    private var retrofit: Retrofit? = null
    fun getWebServices(): WebServices{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(WebServices::class.java)
    }

}