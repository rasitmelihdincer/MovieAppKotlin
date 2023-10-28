package com.example.movieappkotlin.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappkotlin.ui.favorite.FavoriteMoviesAdapter
import com.example.movieappkotlin.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding : FragmentSearchBinding
    private lateinit var searchAdapter : FavoriteMoviesAdapter
    private  val  viewModel  : SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        var job : Job? = null
        binding.searchButton.addTextChangedListener {editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()){
                        viewModel.getSearchMovie(editable.toString())
                    }
                }
            }

        }
        observeData()
    }

    private fun setUpRecyclerView(){
        searchAdapter = FavoriteMoviesAdapter()
        binding.searchRecyclerView.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
    private fun observeData(){
        viewModel.searchMovie.observe(viewLifecycleOwner, Observer {response ->
            searchAdapter.differ.submitList(response.results)

        })
    }


}