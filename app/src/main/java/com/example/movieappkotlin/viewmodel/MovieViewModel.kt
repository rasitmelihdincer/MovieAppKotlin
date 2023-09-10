package com.example.movieappkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

      fun getData(){

        loading.value = true
         viewModelScope.launch {
             try {
                val response = ApiService.getData().getMovies(Constants.TOKEN)
                 if (response.isSuccessful){
                     movieList.postValue(response.body()?.results)
                 } else{
                     if (response.message().isNullOrEmpty()){
                         error.value = "error"
                     } else{
                         error.value = response.message()
                     }
                 }

             } catch (e : Exception){
               error.value = e.message
             } finally {
            loading.value = false
             }
         }

    }

}