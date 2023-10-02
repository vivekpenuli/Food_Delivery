package com.example.food_order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food_order.DataModel.YourDataModel
import com.example.food_order.databinding.MenuitemBinding
import com.example.food_order.databinding.PopularitemBinding

// Replace with your actual package name

class MenuAdapter(private val dataSet: List<YourDataModel>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MenuitemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(private val binding: MenuitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: YourDataModel) {
            binding.imageView.setImageResource(item.imageResource)
            binding.textViewMiddle.text = item.middleText
            binding.textViewFirst.text = item.firstText

        }
    }










}



