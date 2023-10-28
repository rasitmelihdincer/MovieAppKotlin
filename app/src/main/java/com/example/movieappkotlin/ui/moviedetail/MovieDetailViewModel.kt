package com.example.movieappkotlin.ui.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappkotlin.model.MovieDetail
import com.example.movieappkotlin.service.ApiService
import com.example.movieappkotlin.repo.MovieRepository
import com.example.movieappkotlin.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor( val movieRepository: MovieRepository) : ViewModel() {

    var movieDetailList = MutableLiveData<MovieDetail>()
    var isloading = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()

    fun getDetailData(movieId : Int){
        viewModelScope.launch {
        try {
            isloading.value = true
            error.value = false
            val response = movieRepository.getMovieDetailFromApi(movieId)
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
