package com.example.movieappkotlin.Model

import com.google.gson.annotations.SerializedName

data class MovieResponse (
        @SerializedName("results") var results : List<MovieItem>
)