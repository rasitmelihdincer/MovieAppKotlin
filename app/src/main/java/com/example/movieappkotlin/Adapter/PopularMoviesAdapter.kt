package com.example.movieappkotlin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.model.MovieItem
import com.example.movieappkotlin.databinding.PopularMoviesItemsBinding
import com.example.movieappkotlin.util.loadImage

class PopularMoviesAdapter(private val populerMoviesList : List<MovieItem>?,private val movieClickListener : MovieClick) : RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder>() {

    class ViewHolder(val binding: PopularMoviesItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PopularMoviesItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return populerMoviesList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = populerMoviesList?.get(position)
        holder.binding.populerMovieName.text = movie?.originalTitle
        movie?.posterPath?.let { holder.binding.populerMovieImage.loadImage(it) }
        holder.binding.root.setOnClickListener {
            movie?.id?.let {
                movieClickListener.movieClicked(it)

            }
        }

    }
    interface MovieClick {
        fun movieClicked(movieId: Int)
    }
}
