package com.example.movieappkotlin.ui
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.Adapter.FavoriteMoviesAdapter

import com.example.movieappkotlin.databinding.FragmentFavoriteBinding
import com.example.movieappkotlin.local.MovieDatabase
import com.example.movieappkotlin.repo.MovieRepository
import com.example.movieappkotlin.viewmodel.FavoriteViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    lateinit var favoriteAdapter : FavoriteMoviesAdapter
    private val favoriteViewModel : FavoriteViewModel by viewModels()

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
        setUpRecyclerView()
        swipeFunction()
        favoriteViewModel.getSavedMovie().observe(viewLifecycleOwner, Observer {
            favoriteAdapter.differ.submitList(it)
        })


    }
    private fun setUpRecyclerView(){
        favoriteAdapter = FavoriteMoviesAdapter()
        binding.favoriteRecyclerView.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun swipeFunction(){
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val movies = favoriteAdapter.differ.currentList[position]
                favoriteViewModel.deleteMovie(movies)
                view?.let {
                    Snackbar.make(it,"Deleted", Snackbar.LENGTH_LONG).apply {
                        setAction("Undo"){
                            favoriteViewModel.savedMovie(movies)
                        }
                        show()
                    }
                }
            }

        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.favoriteRecyclerView)
        }
    }

}

