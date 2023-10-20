package com.example.movieappkotlin.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappkotlin.model.MovieDetail
import com.example.movieappkotlin.Service.ApiService
import com.example.movieappkotlin.local.MovieDao
import com.example.movieappkotlin.local.MovieDatabase
import com.example.movieappkotlin.repo.MovieRepository
import com.example.movieappkotlin.util.Constants
import kotlinx.coroutines.launch

class MovieDetailViewModel( val movieRepository: MovieRepository) : ViewModel() {

    var movieDetailList = MutableLiveData<MovieDetail>()
    var isloading = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()

    fun getDetailData(movieId : Int){
        viewModelScope.launch {
        try {
            isloading.value = true
            error.value = false
            val response = ApiService.getData().getMovieDetail(movieId.toString(),Constants.TOKEN)
            if (response.isSuccessful){
                isloading.value = false
                movieDetailList.value = response.body()
            } else{
                error.value = true
            }
        } catch (e : Exception){
            error.value = true
            e.printStackTrace()
        } finally {
            isloading.value = false
        }
        }
    }
    fun savedMovie(movie : MovieDetail){
        viewModelScope.launch {
            movieRepository.addFavoriteMovie(movie)
        }
    }
}
