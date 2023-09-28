package com.example.food_order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.food_order.databinding.ActivityRegisterBinding
import com.example.food_order.databinding.ActivityStartBinding

class Register_activity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.login.setOnClickListener {

            val intent = Intent(this,Login_activity::class.java)
            startActivity(intent)

        }
    }
}