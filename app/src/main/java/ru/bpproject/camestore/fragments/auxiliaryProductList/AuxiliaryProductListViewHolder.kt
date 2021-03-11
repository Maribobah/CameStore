package ru.bpproject.camestore.fragments.auxiliaryProductList

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.bpproject.camestore.CartActions
import ru.bpproject.camestore.R
import ru.bpproject.camestore.data.localdb.entity.ProductEntity
import ru.bpproject.camestore.fragments.productItem.ProductItemFragmentDirections
import ru.bpproject.camestore.util.*

class AuxiliaryProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.tv_title_auxiliary_product)
    private val desc: TextView = itemView.findViewById(R.id.tv_desc_auxiliary_product)
    private val price: TextView = itemView.findViewById(R.id.tv_price_auxiliary_product)
    private val image: ImageView = itemView.findViewById(R.id.iv_image_auxiliary_product)
    private val btnAddCart: Button = itemView.findViewById(R.id.btn_add_cart_auxiliary_product)

    fun bind(product: ProductEntity, cartActions: CartActions?) {

        val context = itemView.context

        title.text = product.title
        price.text = getCurrencyFormat().format(product.price)
        desc.text = product.description

        Glide.with(itemView)
            .load(product.image)
            .fitCenter()
            .placeholder(getCircularProgress(context))
            .into(image)

        itemView.setOnClickListener{
            val action = ProductItemFragmentDirections.actionNavProductItemToProductItem(product.id)
            itemView.findNavController().navigate(action)
        }

        btnAddCart.setOnClickListener {
            cartActions?.addToCart(product.id)
        }
    }
}