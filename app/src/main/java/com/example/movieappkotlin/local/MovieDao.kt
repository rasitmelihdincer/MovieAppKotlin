package com.example.movieappkotlin.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappkotlin.model.FavoriteModel


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addMovieFavorite(movie : FavoriteModel)

    @Query("SELECT * FROM favoriteTable")
     fun getFavoriteMovie(): List<FavoriteModel>
/*
    @Query("DELETE FROM favoriteTable WHERE id=:favId")
     fun deleteMovieFromFavorite(favId : Int)

 */

}