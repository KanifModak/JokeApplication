package com.hsbc.jokeapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hsbc.jokeapplication.data.api.ApiHelper
import com.hsbc.jokeapplication.data.api.RetrofitBuilder
import com.hsbc.jokeapplication.data.viewmodels.JokeViewModel
import com.hsbc.jokeapplication.data.viewmodels.JokeViewModelFactory
import com.hsbc.jokeapplication.databinding.FragmentJokeBinding
import com.hsbc.jokeapplication.model.Joke
import com.hsbc.jokeapplication.view.adapters.JokeRecyclerViewAdapter

class JokeFragment : Fragment() {

    val LIMIT = 50
    private lateinit var viewModel: JokeViewModel
    private var binding: FragmentJokeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJokeBinding.inflate(inflater, container, false)
        setUpViewModel()
        viewModel.apply {
            getJokeAPIErrorLiveData().observe(viewLifecycleOwner) {
                binding?.loader?.visibility = View.GONE
                Toast.makeText(activity, "API fail:$it", Toast.LENGTH_LONG).show()
            }
            getJokeLiveData().observe(viewLifecycleOwner) { jokeList ->
                jokeList?.let {
                    binding?.loader?.visibility = View.GONE
                    setupJokeList(it)
                }
            }
        }
        binding?.loader?.visibility = View.VISIBLE
        viewModel.getJokes(LIMIT)

        return binding?.root
    }

    private fun setupJokeList(jokeList: List<Joke>) {
        val recyclerView: RecyclerView? = binding?.jokeRecyclerView
        recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = JokeRecyclerViewAdapter(jokeList)
        }


    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            JokeViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        )[JokeViewModel::class.java]
    }
}