package com.example.movieappkotlin.service

import com.example.movieappkotlin.model.MovieDetail
import com.example.movieappkotlin.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {

     @GET("3/movie/popular")
     suspend fun getMovies(@Header("Authorization") token:String ) : Response<MovieResponse>

     @GET("3/movie/{movieId}")
     suspend fun getMovieDetail(@Path("movieId") movieId : Int,@Header("Authorization") token: String) : Response<MovieDetail>

     @GET("3/search/movie")
     suspend fun getSearchMovie(@Query("query") query : String,@Query("page") page : Int = 1,@Header("Authorization") token: String) : Response<MovieResponse>



}