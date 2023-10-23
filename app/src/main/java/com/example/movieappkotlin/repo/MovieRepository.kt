package com.example.movieappkotlin.repo


import com.example.movieappkotlin.Service.Api
import com.example.movieappkotlin.Service.ApiService
import com.example.movieappkotlin.local.MovieDao
import com.example.movieappkotlin.local.MovieDatabase
import com.example.movieappkotlin.model.MovieDetail
import com.example.movieappkotlin.util.Constants

class MovieRepository (private val movieDb: MovieDatabase  )
                         {
     suspend fun addFavoriteMovie(movie : MovieDetail) = movieDb.getMovieFromDao().addMovieFavorite(movie)
      fun getFavoriteMovie() = movieDb.getMovieFromDao().getFavoriteMovie()

      suspend fun deleteMovie(movie: MovieDetail) = movieDb.getMovieFromDao().deleteFavoriteMovie(movie)

      suspend fun searchMovie(query : String,pageNumber : Int) = ApiService.getData().getSearchMovie(query,pageNumber,Constants.TOKEN)
}