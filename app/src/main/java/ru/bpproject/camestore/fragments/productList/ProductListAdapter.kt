package ru.bpproject.camestore.fragments.productList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.bpproject.camestore.R
import ru.bpproject.camestore.data.localdb.entity.ProductEntity
import ru.bpproject.camestore.fragments.general.ProductDiff

class ProductListAdapter : ListAdapter<ProductEntity, ProductListViewHolder>(ProductDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product, parent, false)
        return ProductListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }
}

