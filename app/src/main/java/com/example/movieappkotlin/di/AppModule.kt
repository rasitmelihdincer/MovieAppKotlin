package com.example.movieappkotlin.di

import android.content.Context
import androidx.room.Room
import com.example.movieappkotlin.local.MovieDao
import com.example.movieappkotlin.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):MovieDatabase =
        Room.databaseBuilder(context,MovieDatabase::class.java,"favorite-database")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideDao(database:MovieDatabase):MovieDao{
        return database.getMovieFromDao()
    }
}

 */