package com.example.food_order.Fragment

import CartBindingAdapter
import PopularBindingAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_order.DataModel.YourDataModel
import com.example.food_order.DataModel.cartdata
import com.example.food_order.R
import com.example.food_order.adapter.MenuAdapter
import com.example.food_order.databinding.FragmentCartBinding
import com.example.food_order.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RsetupRecyclerView()
        PsetupRecyclerView()
    }
    private fun RsetupRecyclerView() {
        val recyclerView = binding.recentlyBoughtRecyclerView
        val dataSet = listOf(
            YourDataModel(R.drawable.menu1, "Middle Text 1", "5"),
            YourDataModel(R.drawable.menu2, "Burger", "6"),
            YourDataModel(R.drawable.menu2, "Burger", "6"),
            YourDataModel(R.drawable.menu2, "Burger", "6"),

            // Add more data items as needed
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PopularBindingAdapter(dataSet)
        recyclerView.adapter = adapter
    }
    private fun PsetupRecyclerView() {
        val recyclerView = binding.previouslyBoughtRecyclerView
        val dataSet = listOf(
            YourDataModel(R.drawable.menu1, "Middle Text 1", "5"),
            YourDataModel(R.drawable.menu2, "Burger", "6"),
            YourDataModel(R.drawable.menu2, "Burger", "6"),
            YourDataModel(R.drawable.menu2, "Burger", "6"),
            YourDataModel(R.drawable.menu2, "Burger", "6"),
            YourDataModel(R.drawable.menu2, "Burger", "6"),
            YourDataModel(R.drawable.menu2, "Burger", "6"),
            YourDataModel(R.drawable.menu2, "Burger", "6"),
            YourDataModel(R.drawable.menu2, "Burger", "6")
            // Add more data items as needed
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PopularBindingAdapter(dataSet)
        recyclerView.adapter = adapter
    }



}