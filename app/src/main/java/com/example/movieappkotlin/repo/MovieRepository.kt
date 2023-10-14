package com.example.movieappkotlin.repo

import com.example.movieappkotlin.model.FavoriteModel
import com.example.movieappkotlin.Service.Api
import com.example.movieappkotlin.local.MovieDao


class MovieRepository(  private val favoriteDb : MovieDao) {

    suspend fun addMovieToFavorite(movie : FavoriteModel){
        favoriteDb.addMovieFavorite(movie)
    }
    suspend fun getMovieToFavorite() : List<FavoriteModel>{
        return favoriteDb.getFavoriteMovie()
    }
    /*
    suspend fun deleteMovieFromFavorite(favId : Int){
        favoriteDb.deleteMovieFromFavorite(favId)
    }

     */
}