package com.hsbc.jokeapplication.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hsbc.jokeapplication.databinding.FragmentJokeBinding
import com.hsbc.jokeapplication.databinding.JokeItemBinding
import com.hsbc.jokeapplication.model.Joke
import com.hsbc.jokeapplication.view.placeholder.PlaceholderContent.PlaceholderItem


class JokeRecyclerViewAdapter(
    private var jokeList: List<Joke>,
) : RecyclerView.Adapter<JokeRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: JokeItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = JokeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            jokeList[position].apply {
                binding.jokeText.text = this.jokeMsg
           }
        }
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return jokeList.size
    }
}