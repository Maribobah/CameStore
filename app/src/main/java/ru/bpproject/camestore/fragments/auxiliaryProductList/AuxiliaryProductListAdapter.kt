package ru.bpproject.camestore.fragments.auxiliaryProductList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.bpproject.camestore.CartActions
import ru.bpproject.camestore.R
import ru.bpproject.camestore.data.localdb.entity.ProductEntity
import ru.bpproject.camestore.fragments.general.ProductDiff

class AuxiliaryProductListAdapter(
    private val cartAction: CartActions?
) :
    ListAdapter<ProductEntity, AuxiliaryProductListViewHolder>(ProductDiff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AuxiliaryProductListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_auxiliary_product, parent, false)
        return AuxiliaryProductListViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuxiliaryProductListViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product, cartAction)
    }
}