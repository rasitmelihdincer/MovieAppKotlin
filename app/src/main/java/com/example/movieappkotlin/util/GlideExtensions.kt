package com.example.movieappkotlin.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movieappkotlin.R


fun ImageView.loadImage(url : String){
    Glide.with(this.context).load(Constants.IMAGE_BASE_URL + url).centerCrop().error(R.drawable.baseline_error_outline_24).into(this)
}