package ru.bpproject.camestore.fragments.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.bpproject.camestore.R
import ru.bpproject.camestore.data.localdb.entity.CategoryEntity

class CatalogAdapter : ListAdapter<CategoryEntity, CatalogViewHolder>(CatalogDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category, parent, false)
        return CatalogViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }

}

object CatalogDiff: DiffUtil.ItemCallback<CategoryEntity>() {
    override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean =
        oldItem.id == newItem.id
}