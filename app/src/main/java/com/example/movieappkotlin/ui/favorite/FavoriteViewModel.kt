package com.example.movieappkotlin.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappkotlin.local.MovieDatabase

import com.example.movieappkotlin.model.MovieDetail
import com.example.movieappkotlin.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(val movieRepository: MovieRepository): ViewModel() {


    fun savedMovie(movie : MovieDetail){
        viewModelScope.launch {
            movieRepository.addFavoriteMovie(movie)
        }
    }

    fun getSavedMovie() = movieRepository.getFavoriteMovie()

    fun deleteMovie(movie: MovieDetail) {
        viewModelScope.launch {
            movieRepository.deleteMovie(movie)
        }
    }
}