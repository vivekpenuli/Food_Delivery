package com.example.food_order.Fragment

import CartBindingAdapter
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
import com.example.food_order.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class bottomsheetfragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomsheetfragmentBinding
    private  lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    val menuItem:ArrayList<YourDataModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBottomsheetfragmentBinding.inflate(inflater,container,false)

        retreiveitem()
     setupRecyclerView()
        return binding.root

    }

    private fun retreiveitem() {

        // Initalizing authentication varubale
        auth = Firebase.auth
        // iNITITALIZING DATABASE VARIABLE
        database = FirebaseDatabase.getInstance()

        val foodref : DatabaseReference =database.reference.child("menu")

        foodref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                menuItem.clear()
                for (foodsnapshot in snapshot.children) {
                    val menuItemData: YourDataModel? = foodsnapshot.getValue(YourDataModel::class.java)
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
        val recyclerView = binding.menurecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MenuAdapter(menuItem)
        recyclerView.adapter = adapter
    }
}