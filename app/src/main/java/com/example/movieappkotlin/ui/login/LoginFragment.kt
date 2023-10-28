package com.example.movieappkotlin.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.movieappkotlin.ui.MainActivity
import com.example.movieappkotlin.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null){
            val action = LoginFragmentDirections.actionLoginFragmentToMovieFragment()
            findNavController().navigate(action)
        }
        binding.loginButton.setOnClickListener {
            val email = binding.usernameText.text.toString()
            val password = binding.passwordText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {

                    val action = LoginFragmentDirections.actionLoginFragmentToMovieFragment()
                    findNavController().navigate(action)
                }.addOnFailureListener {
                    Toast.makeText(context,it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            } else{
                Toast.makeText(context,"Enter email and password", Toast.LENGTH_LONG).show()
            }
        }
        binding.signButton.setOnClickListener {
            val email = binding.usernameText.text.toString()
            val password = binding.passwordText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                auth.createUserWithEmailAndPassword(email,password).addOnFailureListener {
                    Toast.makeText(context,it.localizedMessage, Toast.LENGTH_LONG).show()
                }.addOnSuccessListener {
                    Toast.makeText(context,"Created User", Toast.LENGTH_LONG).show()
                }
            } else{
                Toast.makeText(context,"Enter email and password", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomBar()
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity).showBottomBar()
    }


}