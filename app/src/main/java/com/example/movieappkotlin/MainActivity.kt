package com.example.movieappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.movieappkotlin.databinding.ActivityMainBinding
import com.example.movieappkotlin.local.MovieDao
import com.example.movieappkotlin.local.MovieDatabase
import com.example.movieappkotlin.repo.MovieRepository
import com.example.movieappkotlin.ui.FavoriteFragment
import com.example.movieappkotlin.ui.MovieDetailFragment
import com.example.movieappkotlin.ui.MovieFragment
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2 ) as NavHostFragment
        val navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigationView,navController)

    }


}

