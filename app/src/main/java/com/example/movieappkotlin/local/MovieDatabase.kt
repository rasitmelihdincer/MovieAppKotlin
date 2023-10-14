package com.example.movieappkotlin.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieappkotlin.model.FavoriteModel


@Database(entities = [FavoriteModel::class], version = 1 , exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieFromDao(): MovieDao


    companion object{
        private var INSTANCE: MovieDatabase? = null

        fun getFavoriteMovieDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "favorite-database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
