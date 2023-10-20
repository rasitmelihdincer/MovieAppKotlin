package com.example.movieappkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappkotlin.model.MovieItem
import com.example.movieappkotlin.Service.ApiService
import com.example.movieappkotlin.model.MovieDetail
import com.example.movieappkotlin.util.Constants
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {




    val movieList = MutableLiveData<List<MovieDetail>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    init {
        getData()
    }
      fun getData(){
          loading.value = true
          error.value = false
          viewModelScope.launch {
                try {
                    val response = ApiService.getData().getMovies(Constants.TOKEN)
                    if (response.isSuccessful){
                        movieList.postValue(response.body()?.results)
                        loading.value = false
                    } else{
                        error.value = true

                    }
                } catch (e : Exception){
                    e.printStackTrace()
                    error.value = true
                } finally {
                    loading.value = false

                }
         }

    }
}




