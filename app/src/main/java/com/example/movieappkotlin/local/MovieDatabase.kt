package com.example.movieappkotlin.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieappkotlin.model.MovieDetail


@Database(entities = [MovieDetail::class], version = 1 , exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieFromDao(): MovieDao

    companion object{
        private var INSTANCE: MovieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(LOCK) {
                INSTANCE ?: createDataBase(context).also { INSTANCE = it  }
            }
        }

        private fun createDataBase(context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movie-db"
            ).allowMainThreadQueries().fallbackToDestructiveMigration()
                .build()
        }
    }
}
