package com.example.movieappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.movieappkotlin.databinding.ActivityMainBinding
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
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    replaceFragment(MovieFragment())
                    true
                }
                R.id.settings ->{
                    replaceFragment(FavoriteFragment())
                    true
                }
            else -> {
                false
            }
            }
        }
        setupNav()
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView2,fragment)
        fragmentTransaction.commit()
    }
    private fun setupNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }
    private fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
    private fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE
    }
}
