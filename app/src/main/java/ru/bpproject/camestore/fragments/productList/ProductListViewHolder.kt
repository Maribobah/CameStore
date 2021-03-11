package ru.bpproject.camestore.fragments.productList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.bpproject.camestore.R
import ru.bpproject.camestore.data.localdb.entity.ProductEntity
import ru.bpproject.camestore.util.*

class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val layout: ConstraintLayout = itemView.findViewById(R.id.cl_item_product)
    private val title: TextView = itemView.findViewById(R.id.tv_title)
    private val price: TextView = itemView.findViewById(R.id.tv_price)
    private val image: ImageView = itemView.findViewById(R.id.iv_image)

    fun bind(product: ProductEntity) {

        val context = itemView.context

        title.text = product.title
        price.text = getCurrencyFormat().format(product.price)

        Glide.with(itemView)
            .load(product.image)
            .fitCenter()
            .placeholder(getCircularProgress(context))
            .into(image)

        layout.setOnClickListener{
            val action =ProductListFragmentDirections.actionNavProductListToNavProductItem(product.id)
            it.findNavController().navigate(action)
        }
    }
}