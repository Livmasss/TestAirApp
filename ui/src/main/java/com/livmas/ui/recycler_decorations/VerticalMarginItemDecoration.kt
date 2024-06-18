package com.livmas.ui.recycler_decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.livmas.utils.dpToPx

class VerticalMarginItemDecoration(private val spaceSizeDp: Float) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val spaceSizePx = parent.context.dpToPx(spaceSizeDp)/2

            if (parent.getChildAdapterPosition(view) == 0) {
                bottom = spaceSizePx
                return@with
            }
            if (parent.getChildAdapterPosition(view) == parent.childCount) {
                top = spaceSizePx
                return@with
            }

            bottom = spaceSizePx
            top = spaceSizePx
        }
    }
}