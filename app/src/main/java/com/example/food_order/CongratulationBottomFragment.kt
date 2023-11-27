package com.example.food_order

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.food_order.databinding.FragmentCongratulationBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CongratulationBottomFragment : BottomSheetDialogFragment() {
   private lateinit var  binding: FragmentCongratulationBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCongratulationBottomBinding.inflate(layoutInflater,container,false)
        //return inflater.inflate(R.layout.fragment_congratulation_bottom, container, false)
        binding.gohome.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            requireActivity().finish()
        }
   return  binding.root
    }

}