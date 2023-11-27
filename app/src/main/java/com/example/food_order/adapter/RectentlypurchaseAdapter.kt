package com.example.food_order.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food_order.DataModel.OrderDetails
import com.example.food_order.databinding.PurchaseItemBinding


// Replace with your actual package name
// Note: The file name should be the same as of recylecer view calss name

class RecentlypurchaseAdapter(private val dataSet: List<OrderDetails>) :           // List<cardata> : cardata is the type of data which my list will have
    RecyclerView.Adapter<RecentlypurchaseAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: OrderDetails)
    }
    private var itemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PurchaseItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    // restrict the size of element appearain in recylcerview
    // We can customize acoording to our need how many element do we want in recylerview
    override fun getItemCount() = dataSet.size



inner    class ViewHolder(private val binding: PurchaseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // here we are traversing the list we createed of cart data type and aceesing each element through it
        @SuppressLint("SuspiciousIndentation")
        fun bind(item:OrderDetails) {      // note define the datatype of your data class

            val totalQuantity = item.foodQuantites?.sum()
            binding.ordertotal.text = item.totalPrices
            binding.orderqunatity.text = totalQuantity.toString()
          val ready = item.readytopick
            val co = item.paymnetRecieved
            val colorMap = mapOf(
                true to Color.YELLOW,   // Green for true (accepted)
                false to Color.RED    // Red for false (not accepted)
            )

if(co)
{
    val backgroundColor = Color.BLUE
    binding.orderstatus.setBackgroundColor(backgroundColor ?: Color.WHITE) // Use white as default color

}
           else if (ready)
            {
             val backgroundColor = Color.GREEN
                binding.orderstatus.setBackgroundColor(backgroundColor ?: Color.WHITE) // Use white as default color
            }
            // Set the background color of orderstatus card view
            else {
                val backgroundColor = colorMap[item.orderAccepted]
                binding.orderstatus.setBackgroundColor(
                    backgroundColor ?: Color.WHITE
                ) // Use white as default color
            }
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(position, item)
                }
            }
        }
    }
}