package com.example.movieappkotlin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.Model.FavoriteModel
import com.example.movieappkotlin.databinding.PopularMoviesItemsBinding

class FavoriteMoviesAdapter(val favoriteList : FavoriteModel?) : RecyclerView.Adapter<FavoriteMoviesAdapter.ViewHolder>() {
    class ViewHolder(val binding : PopularMoviesItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PopularMoviesItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println(favoriteList?.name)
        holder.binding.populerMovieName.text = favoriteList?.name
    }
}