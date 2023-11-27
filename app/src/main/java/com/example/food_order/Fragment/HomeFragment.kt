package com.example.food_order.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.food_order.DataModel.YourDataModel
import com.example.food_order.DataModel.allfireabse
import com.example.food_order.FoodItemActivity
import com.example.food_order.R
import com.example.food_order.adapter.MenuAdapter
import com.example.food_order.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private  lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    val menuItem:ArrayList<YourDataModel> = ArrayList()
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
        retreiveitem()
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


    private fun retreiveitem() {

        // Initalizing authentication varubale
        auth = Firebase.auth
        // iNITITALIZING DATABASE VARIABLE
        database = FirebaseDatabase.getInstance()

        val foodref : DatabaseReference =database.reference.child("menu")

        foodref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                menuItem.clear()
                for (foodsnapshot in snapshot.children) {
                    val menuItemData: YourDataModel? = foodsnapshot.getValue(YourDataModel::class.java)
                    //Fetiching entire menu data into my own define list

                    if (menuItemData != null) {
                        menuItem.add(menuItemData)
                    }
                }
                setupRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerViewpopular
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MenuAdapter(menuItem)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MenuAdapter.OnItemClickListener {
            override fun onItemClick(item: YourDataModel) {

                val intent = Intent(requireContext(), FoodItemActivity::class.java)
                val bundle = Bundle()
                bundle.putString("imageResourceId",item.foodImg )
                bundle.putString("itemName", item.foodName)
               bundle.putString("itemprice", item.foodPrice)
                bundle.putString("itemdisc", item.foodDisc)
                bundle.putString("itemingre", item.foodIngred)
                bundle.putString("itemId",item.foodId)


                // Attach the Bundle to the Intent

                // Attach the Bundle to the Intent
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })





    }


}
