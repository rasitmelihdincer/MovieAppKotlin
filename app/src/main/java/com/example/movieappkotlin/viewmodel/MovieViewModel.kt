package com.example.movieappkotlin.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappkotlin.Model.FavoriteModel
import com.example.movieappkotlin.Model.MovieItem
import com.example.movieappkotlin.Model.MovieResponse
import com.example.movieappkotlin.Service.ApiService
import com.example.movieappkotlin.util.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.internal.operators.single.SingleObserveOn
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private val disposable = CompositeDisposable()


    val movieList = MutableLiveData<List<MovieItem>>()
    val favoriteList = FavoriteModel()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

      fun getData(){
          loading.value = true
          error.value = false
          viewModelScope.launch {
                try {
                    val response = ApiService.getData().getMovies(Constants.TOKEN)
                    if (response.isSuccessful){
                        movieList.postValue(response.body()?.results)
                        favoriteList.name = response.body()?.results?.get(5)?.originalTitle
                        println(favoriteList.name)
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




