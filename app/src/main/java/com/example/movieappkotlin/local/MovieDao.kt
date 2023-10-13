package com.example.movieappkotlin.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappkotlin.model.FavoriteModel


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieFavorite(movie : FavoriteModel)

    @Query("SELECT * FROM favoriteTable")
    suspend fun getFavoriteMovie(): List<FavoriteModel>

    @Query("DELETE FROM favoriteTable WHERE id=:favId")
    suspend fun deleteMovieFromFavorite(favId : Int)





}