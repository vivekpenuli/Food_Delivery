package com.example.food_order

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.food_order.DataModel.OrderDetails
import com.example.food_order.databinding.ActivityPayOutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PayOutActivity : AppCompatActivity() {
    lateinit var binding: ActivityPayOutBinding
    private  lateinit var auth: FirebaseAuth
    private  lateinit var name : String
    private  lateinit var phone : String
    private  lateinit var totalAmount : String
    var foodPrice = mutableListOf<String>()
    var foodImage = mutableListOf<String>()
    var foodName = mutableListOf<String>()
    var foodDescription = mutableListOf<String>()
    var foodIngredent = mutableListOf<String>()
    var foodQuantity = mutableListOf<Int>()
    private  lateinit var databaseReference: DatabaseReference
    private lateinit var  userId: String
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference()
        // Set user Data
        setUserData()
 //get user details from firebase
        val intent  = intent
        foodName = intent.getStringArrayListExtra("FoodNmae") as ArrayList<String>
        foodDescription = intent.getStringArrayListExtra("FoodDescription") as ArrayList<String>
        foodImage = intent.getStringArrayListExtra("FoodImage") as ArrayList<String>
        foodPrice = intent.getStringArrayListExtra("FoodPrice") as ArrayList<String>
        foodQuantity = intent.getIntegerArrayListExtra("FoodQuantity") as ArrayList<Int>
        foodIngredent = intent.getStringArrayListExtra("FoodIngredent") as ArrayList<String>

binding.placeorder.setOnClickListener {
    name = binding.orderusername.text.toString().trim()
    phone = binding.orderuserphone.text.toString().trim()
    placeorder()
val bottomsheetDialog = CongratulationBottomFragment()
    bottomsheetDialog.show(supportFragmentManager,"Test")
}

        // calculate the total amount
        totalAmount = "₹" + calculateamount().toString()
        binding.orderusercost.text = totalAmount


    }

    private fun placeorder() {
        userId = auth.currentUser?.uid?:""
        val time = System.currentTimeMillis()
val itemPushKey = databaseReference.child("OrderDetails").push().key
        val orderDetails =OrderDetails(userId,name,foodName,foodImage,foodPrice,foodQuantity,totalAmount,
            phone,false,false,false,itemPushKey,time
        )

        val orderReference = databaseReference.child("OrderDetails").child(itemPushKey!!)
        orderReference.setValue(orderDetails).addOnSuccessListener {

            removeitemfromcart()
            addorderDetails(orderDetails)
            Toast.makeText(this, "Item Added to cart",Toast.LENGTH_SHORT).show()

        }

            .addOnFailureListener {
                Toast.makeText(this, "Failed to Order",Toast.LENGTH_SHORT).show()
            }
    }

    private fun addorderDetails(orderDetails: OrderDetails) {
databaseReference.child("App_user").child(userId).child("BuyHistory")
    .child(orderDetails.itemPushKey!!)
    .setValue(orderDetails).addOnSuccessListener {

    }
    }

    private fun removeitemfromcart() {
        val cartitemReference = databaseReference.child("App_user")
            .child(userId)
            .child("CartItem")
        cartitemReference.removeValue()

    }

    private fun calculateamount(): Int {
var totalamount=0
        for (i in 0 until foodPrice.size)
        {  var price = foodPrice[i].toInt()
            var quantity = foodQuantity[i]
            totalamount += price * quantity

        }

        return totalamount
    }


    private fun setUserData() {
        val user : FirebaseUser? = auth.currentUser
        if (user!=null)
        {
            val userId: String = user.uid
            val userReference = databaseReference.child("App_user").child(userId)

            userReference.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists())
                    {
                        val name : String = snapshot.child("name").getValue(String::class.java)?:""
                        val phone = snapshot.child("phone").getValue(String::class.java)?:""

                   binding.apply {
                       orderusername.text = name
                       orderuserphone.text = phone
                   }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


            })


        }

    }
}