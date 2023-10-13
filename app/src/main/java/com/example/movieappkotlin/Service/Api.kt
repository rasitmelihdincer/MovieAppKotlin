package com.example.movieappkotlin.Service

import com.example.movieappkotlin.model.MovieDetail
import com.example.movieappkotlin.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


interface Api {

     @GET("popular")
     suspend fun getMovies(@Header("Authorization") token:String ) : Response<MovieResponse>

     @GET("{movieId}")
     suspend fun getMovieDetail(@Path("movieId") movieId : String,@Header("Authorization") token: String) : Response<MovieDetail>


}