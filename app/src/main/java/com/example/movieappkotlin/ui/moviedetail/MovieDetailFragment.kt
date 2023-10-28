package com.example.movieappkotlin.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieappkotlin.R

import com.example.movieappkotlin.databinding.FragmentMovieDetailBinding
import com.example.movieappkotlin.model.MovieDetail

import com.example.movieappkotlin.util.loadImage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private lateinit var binding : FragmentMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels()

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
        viewModel.getDetailData(requireArguments().getInt("movieId"))
        binding.backButton.setOnClickListener {
            val action = MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieFragment()
            findNavController().navigate(action)
        }
        binding.favoriteButton.setOnClickListener {
            viewModel.savedMovie(MovieDetail(requireArguments().getInt("movieId"),requireArguments().getString("movieTitle"),requireArguments().getString("moviePoster")))
            binding.favoriteButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.pink)
            Toast.makeText(requireContext(),"Saved",Toast.LENGTH_SHORT).show()
        }
        observeData()

    }


    private fun observeData(){
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