package com.example.movieappkotlin.Service

import com.example.movieappkotlin.Model.FavoriteModel
import com.example.movieappkotlin.Model.MovieItem
import com.example.movieappkotlin.Model.MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface Api {

    @GET("popular")
     suspend fun getMovies(@Header("Authorization") token:String ) : Response<MovieResponse>
     suspend fun getFavorite() : List<FavoriteModel>
}