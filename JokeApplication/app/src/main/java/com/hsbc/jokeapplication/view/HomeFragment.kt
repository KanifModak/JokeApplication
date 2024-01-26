package com.hsbc.jokeapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hsbc.jokeapplication.R
import com.hsbc.jokeapplication.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding?.apply {
            submitButton.setOnClickListener {
                val limit = jokeLimitInput.text
                if (!limit.isNullOrEmpty()) {
                 //   val action = HomeFragmentDirections.actionHomeToJokeFragment()
                    findNavController().navigate(R.id.action_home_to_jokeFragment)
                }
            }
        }
        return binding?.root
    }

}