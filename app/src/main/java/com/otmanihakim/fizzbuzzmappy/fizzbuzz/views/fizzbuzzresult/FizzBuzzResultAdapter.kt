package com.otmanihakim.fizzbuzzmappy.fizzbuzz.views.fizzbuzzresult

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otmanihakim.fizzbuzzmappy.R
import com.otmanihakim.fizzbuzzmappy.databinding.FragmentFizzBuzzItemListBinding

class FizzBuzzResultAdapter(private val results: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_fizz_buzz_item_list, parent, false)
        return FizzBuzzResultsViewHolder(viewItem)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? FizzBuzzResultsViewHolder)?.bind(results[position])
    }

    class FizzBuzzResultsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: FragmentFizzBuzzItemListBinding = FragmentFizzBuzzItemListBinding.bind(view)

        fun bind(result: String) {
            binding.fizzBuzzText.text = result
        }
    }
}