package ru.bpproject.camestore.fragments.productList

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ProductSpaceItemDecoration(private val padding: Int, private val gridSize: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        outRect.left = padding
        outRect.right = if ((itemPosition + 1) % gridSize == 0) {
            padding
        } else 0
        outRect.top = if (itemPosition < gridSize) {
            0
        } else padding
        outRect.bottom = 0
    }

}