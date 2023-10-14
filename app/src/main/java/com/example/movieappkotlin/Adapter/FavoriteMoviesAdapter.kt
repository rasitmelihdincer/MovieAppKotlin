package com.example.movieappkotlin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.movieappkotlin.databinding.PopularMoviesItemsBinding
import com.example.movieappkotlin.model.FavoriteModel
import com.example.movieappkotlin.util.DiffUtilCallback

class FavoriteMoviesAdapter() : ListAdapter<FavoriteModel, FavoriteMoviesAdapter.FavoriteViewHolder>(
    DiffUtilCallback<FavoriteModel>(
        itemsTheSame = { oldItem, newItem ->
            oldItem == newItem
        },
        contentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavoriteViewHolder(
        PopularMoviesItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(currentList[position])

    }



    inner class FavoriteViewHolder(val binding:PopularMoviesItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(favMovie:FavoriteModel) = with(binding){
         //   binding.populerMovieName.text = favMovie.originalTitle
            movie = favMovie


        }
    }
}