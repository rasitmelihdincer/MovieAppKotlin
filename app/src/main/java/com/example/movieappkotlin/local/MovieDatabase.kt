package com.example.movieappkotlin.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieappkotlin.model.FavoriteModel


@Database(entities = [FavoriteModel::class], version = 1 , exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieFromDao(): MovieDao
}