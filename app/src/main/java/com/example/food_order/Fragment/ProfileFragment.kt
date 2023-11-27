package com.example.food_order.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.food_order.R
import com.example.food_order.databinding.FragmentCartBinding
import com.example.food_order.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileFragment : Fragment() {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity?.window?.statusBarColor = resources.getColor(androidx.appcompat.R.color.material_blue_grey_900)

        setUserData()

    }

    private fun setUserData() {
        val userId = auth.currentUser?.uid
        if(userId!=null)
        {
            val userReference = database.getReference("App_user").child(userId)

            userReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists())
                    {
                        if(snapshot.exists())
                        {
                            val name = snapshot.child("name").getValue(String::class.java)
                            val phone =snapshot.child("phone").getValue(String::class.java)
                            val email = snapshot.child("email").getValue(String::class.java)

                            binding.userprifilename.text = name
                            binding.profielemail.text = email
                            binding.profilephone.text = phone
                           // binding.profilerest.text = userProfile.restaurentname

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }



}