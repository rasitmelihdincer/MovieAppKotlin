package com.example.movieappkotlin.Service

import com.example.movieappkotlin.Model.MovieItem
import com.example.movieappkotlin.Model.MovieResponse
import com.example.movieappkotlin.util.Constants
import io.reactivex.rxjava3.core.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.HTTP
import java.util.concurrent.TimeUnit


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