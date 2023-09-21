package com.example.movieappkotlin.ui

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.Adapter.MovieClick
import com.example.movieappkotlin.Adapter.PopularMoviesAdapter
import com.example.movieappkotlin.Model.FavoriteModel
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
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
             }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMovieBinding.inflate(inflater,container,false)
        val layoutManeger : RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding.popularMovieRecyclerView.layoutManager = layoutManeger
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
        observeLiveData()

    }
    private fun observeLiveData(){
        viewModel.movieList.observe(viewLifecycleOwner){
            popularMoviesAdapter = PopularMoviesAdapter(it, object : MovieClick{
                override fun movieClicked(movieId: Int) {
                    movieId?.let {
                        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(it)
                        findNavController().navigate(action)
                    }
                }


            })
            binding.popularMovieRecyclerView.adapter = popularMoviesAdapter

        }
        viewModel.loading.observe(viewLifecycleOwner){
            if (it == true){
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
        viewModel.error.observe(viewLifecycleOwner){
            if (it == true){
                binding.errorText.visibility = View.VISIBLE
                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
            } else {
                binding.errorText.visibility = View.GONE
            }
        }
    }


}