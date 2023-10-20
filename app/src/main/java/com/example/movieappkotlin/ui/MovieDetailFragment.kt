package com.example.movieappkotlin.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.movieappkotlin.R
import com.example.movieappkotlin.databinding.FragmentMovieDetailBinding
import com.example.movieappkotlin.local.MovieDao
import com.example.movieappkotlin.local.MovieDatabase
import com.example.movieappkotlin.model.MovieDetail
import com.example.movieappkotlin.repo.MovieRepository

import com.example.movieappkotlin.util.loadImage
import com.example.movieappkotlin.viewmodel.FavoriteViewModel
import com.example.movieappkotlin.viewmodel.MovieDetailViewModel



class MovieDetailFragment : Fragment() {
    private lateinit var binding : FragmentMovieDetailBinding
    private lateinit var  viewModel : MovieDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = MovieRepository(MovieDatabase(requireContext()))
        viewModel = MovieDetailViewModel(repository)
        viewModel.getDetailData(requireArguments().getInt("movieId"))
        binding.backButton.setOnClickListener {
            val action = MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieFragment()
            findNavController().navigate(action)
        }

        binding.favoriteButton.setOnClickListener {
            viewModel.savedMovie(MovieDetail())
            Toast.makeText(requireContext(),"saved",Toast.LENGTH_LONG).show()
        }
        observeData()
    }

    fun observeData(){
        viewModel.isloading.observe(viewLifecycleOwner){
             if (it == true){
                 binding.progressBar2.visibility = View.VISIBLE
             } else {
                 binding.progressBar2.visibility = View.GONE
             }
        }

        viewModel.error.observe(viewLifecycleOwner){
            if (it == true){
                binding.errorText.visibility = View.VISIBLE
            } else {
                binding.errorText.visibility = View.GONE
            }
        }
        viewModel.movieDetailList.observe(viewLifecycleOwner){
            it.backdropPath?.let { it1 -> binding.imageView4.loadImage(it1) }
            binding.movieTime.text = "${it.runtime.toString()} m"
            binding.movieSummary.text = it.overview
            binding.movieVote.text = it.voteAverage.toString()
            binding.movieDate.text = it.releaseDate

        }

    }




}