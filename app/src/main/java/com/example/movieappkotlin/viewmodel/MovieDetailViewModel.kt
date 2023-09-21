package com.example.movieappkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappkotlin.Model.MovieDetail
import com.example.movieappkotlin.Service.ApiService
import com.example.movieappkotlin.util.Constants
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {

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
}