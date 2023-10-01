package com.example.food_order.Fragment

import CartBindingAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_order.DataModel.YourDataModel
import com.example.food_order.DataModel.cartdata
import com.example.food_order.R
import com.example.food_order.databinding.FragmentCartBinding
import com.example.food_order.databinding.FragmentHomeBinding


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.carrecyclerview
        val dataset = listOf(
            cartdata(R.drawable.menu1, "Burger", "100", 5),
            cartdata(R.drawable.menu2, "pizza", "600", 0),
            cartdata(R.drawable.menu3, "Burger", "100", 4),
            cartdata(R.drawable.menu4, "Chowmin", "400", 2),
            cartdata(R.drawable.menu5, "Burger", "500", 1),
            cartdata(R.drawable.menu6, "Burger", "800", 4),
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CartBindingAdapter(dataset)
        recyclerView.adapter = adapter
    }
}
