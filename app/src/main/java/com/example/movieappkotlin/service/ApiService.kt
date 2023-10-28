package com.example.movieappkotlin.service

import com.example.movieappkotlin.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {

    fun getData() : Api{
        val client : OkHttpClient = OkHttpClient.Builder().build()
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(Api::class.java)
    }

}