package ru.bpproject.camestore.fragments.general

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bpproject.camestore.R

class StringListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.tv_item_string)

    fun bind(value: String) {
        title.text = value
    }
}