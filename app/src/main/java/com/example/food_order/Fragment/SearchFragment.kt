package com.example.food_order.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_order.DataModel.YourDataModel
import com.example.food_order.FoodItemActivity
import com.example.food_order.adapter.MenuAdapter
import com.example.food_order.databinding.FragmentSearchBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private val menuItem: ArrayList<YourDataModel> = ArrayList()
    private val filteredItems: ArrayList<YourDataModel> = ArrayList() // To store filtered items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // Initialize authentication and database
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()

        setupRecyclerView()


        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Filter items as the user types
                filterItems(newText.orEmpty())
                return true
            }
        })


        retrieveItems()

        return binding.root
    }



    private fun setupRecyclerView() {
        // you are moving to next activiity on clinking the recylerview item
        val recyclerView = binding.searchrecycler
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MenuAdapter(filteredItems)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MenuAdapter.OnItemClickListener {
            override fun onItemClick(item: YourDataModel) {
                // Handle item click here
                // For example, open a new activity
                val intent = Intent(requireContext(), FoodItemActivity::class.java)
                // Pass any data you need to the new activity using intent.putExtra

//                println("the food name is ${item.foodName}")
//                println("the food name is ${item.foodPrice}")


                // Create a Bundle to store data
                // Create a Bundle to store data
                val bundle = Bundle()
                bundle.putString("imageResourceId",item.foodImg )
                bundle.putString("itemName", item.foodName)
                bundle.putString("itemprice", item.foodPrice)
                bundle.putString("itemdisc", item.foodDisc)
                bundle.putString("itemingre", item.foodIngred)

                // Attach the Bundle to the Intent

                // Attach the Bundle to the Intent
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })





    }


    private fun retrieveItems() {
        val foodRef: DatabaseReference = database.reference.child("menu")

        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                menuItem.clear()
                for (foodSnapshot in snapshot.children) {
                    val menuItemData: YourDataModel? = foodSnapshot.getValue(YourDataModel::class.java)
                    menuItemData?.let { menuItem.add(it) }
                }
                filterItems("") // Initialize with all items
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterItems(query: String) {
        filteredItems.clear()
        for (item in menuItem) {
            if (item.foodName?.contains(query, ignoreCase = true) == true) {
                filteredItems.add(item)
            }
        }
        binding.searchrecycler.adapter?.notifyDataSetChanged()
    }
}