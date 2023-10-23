package com.example.movieappkotlin.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappkotlin.model.MovieDetail


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addMovieFavorite(movie : MovieDetail)

    @Query("SELECT * FROM movieTable")
     fun getFavoriteMovie(): LiveData<List<MovieDetail>>

     @Delete
     fun deleteFavoriteMovie(movieDetail: MovieDetail)

}