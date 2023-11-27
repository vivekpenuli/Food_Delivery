package com.example.food_order.Fragment

import CartBindingAdapter
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_order.DataModel.AddtoFirebase
import com.example.food_order.DataModel.YourDataModel
import com.example.food_order.DataModel.cartdata
import com.example.food_order.FoodItemActivity
import com.example.food_order.PayOutActivity
import com.example.food_order.R
import com.example.food_order.adapter.MenuAdapter
import com.example.food_order.databinding.FragmentCartBinding
import com.example.food_order.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private  lateinit var auth: FirebaseAuth
    private var OrderId : String ?= null
    private lateinit var database: FirebaseDatabase
    val CartmenuItem:ArrayList<AddtoFirebase> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.processed.isEnabled = false
        retreiveitem()
        setupRecyclerView()
        binding.processed.setOnClickListener {
            // Get Order Item details before when clciking on proceed button
            getorderdetails()

        }
    }

    private fun getorderdetails() {
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid ?: ""
        val orderReference : DatabaseReference =database.reference.child("App_user").child(userId).child("CartItem")
val foodName = mutableListOf<String>()
        val foodPrice = mutableListOf<String>()
        val foodImage = mutableListOf<String>()
        val foodDescription = mutableListOf<String>()
        val foodIngredent = mutableListOf<String>()
        val foodQuantity = mutableListOf<Int>()

        orderReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodsnapshot in snapshot.children) {
                    val orderItemData: AddtoFirebase? = foodsnapshot.getValue(AddtoFirebase::class.java)
                    //Fetiching entire menu data into my own define list
                    // add item detials in our list
                    orderItemData?.foodName?.let { foodName.add(it) }
                    orderItemData?.foodPrice?.let { foodPrice.add(it) }
                    orderItemData?.foodImg?.let { foodImage.add(it) }
                    orderItemData?.foodDisc?.let { foodDescription.add(it) }
                    orderItemData?.foodIngred?.let { foodIngredent.add(it) }
                    orderItemData?.fooddQuantity?.let { foodQuantity.add(it) }

                }
                orderNow(foodName,foodPrice,foodQuantity,foodIngredent,foodDescription,foodImage)

            }



            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    private fun orderNow(foodName: MutableList<String>,
                         foodPrice: MutableList<String>,
                         foodQuantity: MutableList<Int>,
                         foodIngredent: MutableList<String>,
                         foodDescription: MutableList<String>,
                         foodImage: MutableList<String>) {

if (isAdded && context!=null)
{
    val intent = Intent(requireContext(), PayOutActivity::class.java)
    intent.putExtra("FoodNmae", foodName as ArrayList<String>)
    intent.putExtra("FoodPrice", foodPrice as ArrayList<String>)
    intent.putExtra("FoodQuantity", foodQuantity as ArrayList<Int>)
    intent.putExtra("FoodIngredent", foodIngredent as ArrayList<String>)
    intent.putExtra("FoodDescription", foodDescription as ArrayList<String>)
    intent.putExtra("FoodImage", foodImage as ArrayList<String>)
startActivity(intent)
}

    }
    private fun retreiveitem() {

        // Initalizing authentication varubale
        auth = Firebase.auth
        // iNITITALIZING DATABASE VARIABLE
        database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid ?: ""
        val foodref : DatabaseReference =database.reference.child("App_user").child(userId).child("CartItem")

        foodref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                CartmenuItem.clear()
                for (foodsnapshot in snapshot.children) {
                    val cartItemData: AddtoFirebase? = foodsnapshot.getValue(AddtoFirebase::class.java)
                    //Fetiching entire menu data into my own define list

                    if (cartItemData != null) {
                        CartmenuItem.add(cartItemData)
                    }
                }

                // Enable or disable the button based on the presence of items
                binding.processed.isEnabled = CartmenuItem.isNotEmpty()

                setupRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun setupRecyclerView() {
        // Initalizing authentication varubale
        auth = Firebase.auth
        // iNITITALIZING DATABASE VARIABLE
        database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid ?: ""

        val recyclerView = binding.carrecyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CartBindingAdapter(CartmenuItem)
        recyclerView.adapter = adapter

        val databaseReference = database.reference.child("App_user").child(userId).child("CartItem")

        adapter.onDeleteClickListener = { position ->
            val item = adapter.dataSet[position]
            val foodName = item.foodId // Assuming foodName is unique
/// Always use Id for refernce never use other thing
            // Remove the item from Firebase Realtime Database using food name as the key
            databaseReference.child(foodName!!).removeValue()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Remove the item from the RecyclerView
                        adapter.dataSet.removeAt(position)
                        adapter.notifyItemRemoved(position)

                        binding.processed.isEnabled = CartmenuItem.isNotEmpty()

                    } else {
                        // Handle the error when deleting from Firebase
                        Log.e("FirebaseDelete", "Failed to delete: ${task.exception}")
                    }
                }
        }



    }

}
