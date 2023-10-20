package com.example.movieappkotlin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappkotlin.databinding.FragmentMovieDetailBinding

import com.example.movieappkotlin.databinding.PopularMoviesItemsBinding
import com.example.movieappkotlin.model.MovieDetail

import com.example.movieappkotlin.util.DiffUtilCallback
import com.example.movieappkotlin.util.loadImage

class FavoriteMoviesAdapter : RecyclerView.Adapter<FavoriteMoviesAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(val binding : PopularMoviesItemsBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<MovieDetail>(){
        override fun areItemsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(PopularMoviesItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val favoriteMovie = differ.currentList[position]
        holder.itemView.apply {
            holder.binding.populerMovieName.text = favoriteMovie.originalTitle
            favoriteMovie.posterPath?.let { holder.binding.populerMovieImage.loadImage(it) }
            setOnClickListener {
                onItemClickListener?.let {
                    it(favoriteMovie) }
            }

        }
    }

    private var onItemClickListener : ((MovieDetail) -> Unit)? = null

    fun setOnItemClickListener(listener : (MovieDetail) -> Unit){
        onItemClickListener = listener
    }


}