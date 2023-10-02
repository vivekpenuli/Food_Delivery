package com.example.food_order.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_order.DataModel.YourDataModel
import com.example.food_order.R
import com.example.food_order.adapter.MenuAdapter
import com.example.food_order.databinding.FragmentBottomsheetfragmentBinding
import com.example.food_order.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        setupRecyclerView()

        return binding.root

    }


    private fun setupRecyclerView() {
        val recyclerView = binding.searchrecycler
        val dataset1 = listOf(
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
        val adapter = MenuAdapter(dataset1)
        recyclerView.adapter = adapter
    }


}