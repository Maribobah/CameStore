package ru.bpproject.camestore.fragments.catalog

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.bpproject.camestore.R
import ru.bpproject.camestore.data.localdb.entity.CategoryEntity


class CatalogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.tv_category)
    private val image: ImageView = itemView.findViewById(R.id.iv_category)
    private val row: ConstraintLayout = itemView.findViewById(R.id.row_category)

    fun bind(category: CategoryEntity) {
        name.text = category.name
        image.setImageResource(
            itemView.resources.getIdentifier(
                category.image,
                "drawable",
                itemView.context.packageName
            )
        )
        row.setOnClickListener {
            val action = CatalogFragmentDirections.actionCatalogToProductList(category.id)
            itemView.findNavController().navigate(action)
        }
    }
}