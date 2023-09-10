package com.example.movieappkotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movieappkotlin.Model.MovieItem
import com.example.movieappkotlin.Model.MovieResponse
import com.example.movieappkotlin.R
import com.example.movieappkotlin.databinding.FragmentMovieBinding
import com.example.movieappkotlin.viewmodel.MovieViewModel
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class MovieFragment : Fragment() {

    private lateinit var binding : FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
             }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMovieBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.movieList.observe(viewLifecycleOwner){ list ->
            println(list[1].title)
        }
    }


}