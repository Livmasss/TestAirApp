package com.livmas.utils

import java.text.DecimalFormat

object MyDecimalFormatter {
    fun formatPrice(number: Int): String {
        val dec = DecimalFormat("###,###,###,###,###")
        return dec.format(number).replace(',', ' ')
    }
}