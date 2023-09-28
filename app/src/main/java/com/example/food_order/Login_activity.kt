package com.example.food_order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.food_order.databinding.ActivityLoginBinding
import com.example.food_order.databinding.ActivityStartBinding

class Login_activity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.register.setOnClickListener {

            val intent = Intent(this,Register_activity::class.java)
            startActivity(intent)
        }

    }
}