package com.example.movieappkotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.Adapter.FavoriteMoviesAdapter
import com.example.movieappkotlin.Model.FavoriteModel
import com.example.movieappkotlin.R
import com.example.movieappkotlin.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteMoviesAdapter: FavoriteMoviesAdapter
    private var favorites : FavoriteModel? = null
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteMoviesAdapter = FavoriteMoviesAdapter(favorites)
        binding.favoriteRecyclerView.adapter = favoriteMoviesAdapter

    }
}