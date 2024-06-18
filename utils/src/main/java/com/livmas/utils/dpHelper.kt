package com.livmas.utils

import android.content.Context

fun Context.dpToPx(dp: Float): Int {
    return (dp * resources.displayMetrics.density).toInt()
}

fun Context.pxToDp(px: Int): Float {
    return px / resources.displayMetrics.density
}