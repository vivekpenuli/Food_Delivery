package com.example.food_order.Fragment

import PopularBindingAdapter
import android.os.Bundle
import android.os.TestLooperManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.food_order.DataModel.YourDataModel
import com.example.food_order.R


import com.example.food_order.databinding.ActivityLoginBinding
import com.example.food_order.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewmore.setOnClickListener{
            val bottomSheetDialog = bottomsheetfragment()
            bottomSheetDialog.show(parentFragmentManager,bottomSheetDialog.tag)

        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupImageSlider()
        setupRecyclerView()
    }

    private fun setupImageSlider() {
        val imageList = listOf(
            SlideModel(R.drawable.banner1, ScaleTypes.FIT),
            SlideModel(R.drawable.banner2, ScaleTypes.FIT),
            SlideModel(R.drawable.banner3, ScaleTypes.FIT),
            SlideModel(R.drawable.banner1, ScaleTypes.FIT),
            SlideModel(R.drawable.banner2, ScaleTypes.FIT)
        )

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)

        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {
                // Handle double click if needed
            }

            override fun onItemSelected(position: Int) {
                val itemMessage = "Selected Image is $position"
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerViewpopular
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
