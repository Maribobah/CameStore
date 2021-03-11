package ru.bpproject.camestore.fragments.general

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.bpproject.camestore.R

class StringListAdapter : ListAdapter<String, StringListViewHolder>(StringDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_string, parent, false)
        return StringListViewHolder(view)
    }

    override fun onBindViewHolder(holder: StringListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object StringDiff : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem
}