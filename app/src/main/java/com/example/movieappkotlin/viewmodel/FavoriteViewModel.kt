package com.example.movieappkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappkotlin.local.MovieDatabase
import com.example.movieappkotlin.model.FavoriteModel
import com.example.movieappkotlin.repo.MovieRepository
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {
    private val movieRepo : MovieRepository? = null
    val getFavoriteMovies = MutableLiveData<List<FavoriteModel>>()

init {
    getFavorite()
 }
    private fun getFavorite(){
        viewModelScope.launch {
            getFavoriteMovies.postValue(movieRepo?.getMovieToFavorite())
        }

    }
}