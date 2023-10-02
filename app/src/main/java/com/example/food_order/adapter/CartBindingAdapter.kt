import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food_order.DataModel.cartdata
import com.example.food_order.databinding.CartitemBinding

// Replace with your actual package name

class CartBindingAdapter(private val dataSet: List<cartdata>) :
    RecyclerView.Adapter<CartBindingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CartitemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(private val binding: CartitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: cartdata) {
            binding.dishImageView.setImageResource(item.imageResource)
            binding.dishNameTextView.text = item.dishname
            binding.dishCostTextView.text = item.dishprice
            binding.itemCountTextView.text = item.dishcount.toString()

        }
    }
}



