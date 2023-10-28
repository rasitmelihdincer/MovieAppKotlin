package com.example.movieappkotlin.repo


import com.example.movieappkotlin.service.ApiService
import com.example.movieappkotlin.local.MovieDatabase
import com.example.movieappkotlin.model.MovieDetail
import com.example.movieappkotlin.util.Constants
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieDb: MovieDatabase){

    suspend fun addFavoriteMovie(movie : MovieDetail) = movieDb.getMovieFromDao().addMovieFavorite(movie)

    fun getFavoriteMovie() = movieDb.getMovieFromDao().getFavoriteMovie()

    suspend fun deleteMovie(movie: MovieDetail) = movieDb.getMovieFromDao().deleteFavoriteMovie(movie)

    suspend fun searchMovie(query : String,pageNumber : Int) = ApiService.getData().getSearchMovie(query,pageNumber,Constants.TOKEN)

    suspend fun getMovieFromApi() = ApiService.getData().getMovies(Constants.TOKEN)

    suspend fun getMovieDetailFromApi(movieId : Int) = ApiService.getData().getMovieDetail(movieId,Constants.TOKEN)
}