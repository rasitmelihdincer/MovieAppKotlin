package com.example.movieappkotlin.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.databinding.FragmentMovieBinding

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding : FragmentMovieBinding
    private val  viewModel  : MovieViewModel by viewModels()
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var auth: FirebaseAuth

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
        observeLiveData()
        auth = Firebase.auth
        binding.logoutButton.setOnClickListener {
            auth.signOut()
            val action = MovieFragmentDirections.actionMovieFragmentToLoginFragment()
            findNavController().navigate(action)
        }

    }
    private fun observeLiveData(){
        viewModel.movieList.observe(viewLifecycleOwner){
            popularMoviesAdapter = PopularMoviesAdapter(it, object : PopularMoviesAdapter.MovieClick{
                override fun movieClicked(movieId: Int, movieTitle: String,moviePoster : String) {
                    val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
                            movieId, movieTitle, moviePoster,

                            )
                    findNavController().navigate(action)
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