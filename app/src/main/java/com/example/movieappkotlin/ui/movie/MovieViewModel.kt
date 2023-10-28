package com.example.movieappkotlin.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappkotlin.model.MovieDetail
import com.example.movieappkotlin.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor( val movieRepository: MovieRepository) : ViewModel() {


    val movieList = MutableLiveData<List<MovieDetail>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    init {
        getData()
    }
      private fun getData(){
          loading.value = true
          error.value = false
          viewModelScope.launch {
                try {
                    val response = movieRepository.getMovieFromApi()
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




