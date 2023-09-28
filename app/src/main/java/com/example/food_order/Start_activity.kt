package com.example.food_order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.food_order.databinding.ActivityStartBinding

class Start_activity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.nextmove.setOnClickListener {

            val intent = Intent(this,Login_activity::class.java)
            startActivity(intent)
            finish()

        }
    }
}