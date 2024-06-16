package com.livmas.air_tikets.ui.home.music_adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.livmas.utils.dpToPx


class HorizontalMarginItemDecoration(private val spaceSizeDp: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0)
                return@with

            left = parent.context.dpToPx(spaceSizeDp)
        }
    }
}