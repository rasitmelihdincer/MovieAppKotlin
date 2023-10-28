package com.example.movieappkotlin.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappkotlin.model.MovieResponse
import com.example.movieappkotlin.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {

    var searchMovie = MutableLiveData<MovieResponse>()
    var searchPage = 1


    fun getSearchMovie(query : String){
        viewModelScope.launch {
            val response = movieRepository.searchMovie(query,searchPage)
            searchMovie.postValue(response.body())
        }

    }


}