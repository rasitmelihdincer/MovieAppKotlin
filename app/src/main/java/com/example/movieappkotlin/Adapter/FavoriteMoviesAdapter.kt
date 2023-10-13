package com.example.movieappkotlin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.movieappkotlin.databinding.PopularMoviesItemsBinding
import com.example.movieappkotlin.model.FavoriteModel

class FavoriteMoviesAdapter(val favoriteList : List<FavoriteModel>?) : RecyclerView.Adapter<FavoriteMoviesAdapter.ViewHolder>() {
    class ViewHolder(val binding : PopularMoviesItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PopularMoviesItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.populerMovieName.text = favoriteList?.get(position)?.title
    }
}