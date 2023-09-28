package com.example.food_order

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var NavController: NavController = findNavController(R.id.fragmentContainerView)
        var bottonav : BottomNavigationView =findViewById(R.id.bottomNavigationView)
        bottonav.setupWithNavController(NavController)

    }
}