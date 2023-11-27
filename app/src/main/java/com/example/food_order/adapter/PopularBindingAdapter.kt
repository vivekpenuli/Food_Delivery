import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_order.DataModel.YourDataModel
import com.example.food_order.databinding.PopularitemBinding

// Replace with your actual package name

class PopularBindingAdapter(private val dataSet: List<YourDataModel>) :
    RecyclerView.Adapter<PopularBindingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(PopularitemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(private val binding: PopularitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: YourDataModel) {
            binding.textViewMiddle.text = item.foodName
            binding.textViewFirst.text = item.foodPrice
            val uriString: String = item.foodImg.toString()
            val uri: Uri = Uri.parse(uriString)
            Glide.with(binding.root.context) // Use the context from the root view
                .load(uri)
                .into(binding.imageView) // Replace 'imageView' wit

        }
    }
}



