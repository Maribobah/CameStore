package ru.bpproject.camestore.fragments.general

import androidx.recyclerview.widget.DiffUtil
import ru.bpproject.camestore.data.localdb.entity.ProductEntity

object ProductDiff : DiffUtil.ItemCallback<ProductEntity>() {
    override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean =
        oldItem.id == newItem.id
}