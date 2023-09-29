package com.example.food_order.Fragment

import PopularBindingAdapter
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
binding = FragmentHomeBinding.inflate(inflater,container,false)

    return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner1,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner2,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner3,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner1,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner2,ScaleTypes.FIT))



        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList,ScaleTypes.FIT)

        imageSlider.setItemClickListener(object :ItemClickListener{
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val itemPosition = imageList[position]
                val itemMessage = "Selected Image is $position"
                Toast.makeText(requireContext(),itemMessage,Toast.LENGTH_SHORT).show()

            }

        })
        val recyclerView = binding.recyclerViewpopular // Replace with your actual RecyclerView ID

        val dataSet = listOf(
            YourDataModel(R.drawable.menu1, "Middle Text 1", "5"),
            YourDataModel(R.drawable.menu2, "Burger", "6"),
            YourDataModel(R.drawable.menu1, "Middle Text 1", "9"),
            YourDataModel(R.drawable.menu3, "Middle Text 2", "34"),
            YourDataModel(R.drawable.menu4, "Middle Text 1", "300"),
            YourDataModel(R.drawable.menu4, "pizza", "100"),
            YourDataModel(R.drawable.menu5, "popcorn", "900"),


            // Add more data items as needed
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PopularBindingAdapter(dataSet)
        recyclerView.adapter = adapter

    }
    companion object {

    }
}