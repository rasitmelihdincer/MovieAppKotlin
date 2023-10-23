package com.example.movieappkotlin.di

import android.app.Application
import android.content.Context
import com.example.movieappkotlin.local.MovieDao
import com.example.movieappkotlin.local.MovieDatabase
import com.example.movieappkotlin.repo.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataBase(context : Application) : MovieDatabase{
        return MovieDatabase.invoke(context)
    }

    @Singleton
    @Provides
    fun provideDao(moviedb : MovieDatabase) : MovieDao{
        return moviedb.getMovieFromDao()
    }



}