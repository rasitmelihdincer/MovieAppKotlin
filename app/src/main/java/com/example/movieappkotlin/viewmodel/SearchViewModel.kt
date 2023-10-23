package com.example.movieappkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.movieappkotlin.model.MovieDetail
import com.example.movieappkotlin.model.MovieItem
import com.example.movieappkotlin.model.MovieResponse
import com.example.movieappkotlin.repo.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class SearchViewModel(val movieRepository: MovieRepository) : ViewModel() {

    var searchMovie = MutableLiveData<MovieResponse>()
    var searchPage = 1


    fun getSearchMovie(query : String){
        viewModelScope.launch {
            val response = movieRepository.searchMovie(query,searchPage)
            searchMovie.postValue(response.body())
        }

    }


}