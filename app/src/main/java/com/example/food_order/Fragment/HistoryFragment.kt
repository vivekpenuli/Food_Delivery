package com.example.food_order.Fragment

import android.content.Intent
import com.example.food_order.adapter.RecentlypurchaseAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_order.DataModel.OrderDetails
import com.example.food_order.DataModel.YourDataModel
import com.example.food_order.UserHistoryActivity
import com.example.food_order.adapter.MenuAdapter
import com.example.food_order.databinding.FragmentHistoryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private  lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    val menuItem:ArrayList<YourDataModel> = ArrayList()
    private lateinit var userId: String
val recentlybuyitemlist:ArrayList<OrderDetails> = ArrayList()

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

        // Retrive and display the user order details


        Rretreiveitem()
        //Pretreiveitem()


    }
    private fun Rretreiveitem() {

        // Initalizing authentication varubale

        // iNITITALIZING DATABASE VARIABLE
        database = FirebaseDatabase.getInstance()
        auth = Firebase.auth
        userId = auth.currentUser?.uid ?: ""

        val foodref : DatabaseReference =database.reference.child("App_user").child(userId).child("BuyHistory")

        foodref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                recentlybuyitemlist.clear()
                for (foodsnapshot in snapshot.children) {
                    val menuItemData: OrderDetails? = foodsnapshot.getValue(OrderDetails::class.java)
                    if (menuItemData != null) {
                        recentlybuyitemlist.add(menuItemData)
                    }
                }
                recentlybuyitemlist.reverse()
                setupRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recentlyBoughtRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecentlypurchaseAdapter(recentlybuyitemlist)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : RecentlypurchaseAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, item: OrderDetails) {
                val intent = Intent(requireActivity(), UserHistoryActivity::class.java)
                val bundle = Bundle()
                bundle.putString("username",item.userName)
                //    bundle.putString("firebaseKey", item.firebaseKey)
                val stringList = item.foodNames?.joinToString(",")
                bundle.putString("foodNames", stringList)

                bundle.putString("userUid",item.userUid)
                val stringListimage = item.foodImages?.joinToString(",")
                bundle.putString("foodimg",stringListimage)

                bundle.putIntegerArrayList("foodquan",
                    item.foodQuantites?.let { ArrayList(it) })

                bundle.putString("orderId",item.itemPushKey)

                bundle.putString("phone",item.phoneNumber)

                val stringListprice = item.foodPrices?.joinToString(",")
                bundle.putString("foodprice",stringListprice)
                // Attach the Bundle to the Intent

                bundle.putString("totalprice",item.totalPrices)
                // Attach the Bundle to the Intent
                intent.putExtras(bundle)
                startActivity(intent)
            }

        })

    }







}