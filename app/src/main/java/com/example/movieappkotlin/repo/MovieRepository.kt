package com.example.movieappkotlin.repo


import com.example.movieappkotlin.Service.Api
import com.example.movieappkotlin.local.MovieDao
import com.example.movieappkotlin.local.MovieDatabase
import com.example.movieappkotlin.model.MovieDetail

class MovieRepository (private val movieDb: MovieDatabase)
                         {
     suspend fun addFavoriteMovie(movie : MovieDetail) = movieDb.getMovieFromDao().addMovieFavorite(movie)
      fun getFavoriteMovie() = movieDb.getMovieFromDao().getFavoriteMovie()
}