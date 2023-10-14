package com.example.movieappkotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.Adapter.FavoriteMoviesAdapter
import com.example.movieappkotlin.model.FavoriteModel
import com.example.movieappkotlin.databinding.FragmentFavoriteBinding
import com.example.movieappkotlin.viewmodel.FavoriteViewModel


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val favAdapter by lazy { FavoriteMoviesAdapter() }
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    var favorites : List<FavoriteModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        val layoutManeger : RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding.favoriteRecyclerView.layoutManager = layoutManeger
    //    favAdapter = FavoriteMoviesAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoriteRecyclerView.adapter = favAdapter
        observeData()
    }


    private fun observeData(){
        favoriteViewModel.getFavoriteMovies.observe(viewLifecycleOwner){
            favAdapter.submitList(it)
        }
    }

}