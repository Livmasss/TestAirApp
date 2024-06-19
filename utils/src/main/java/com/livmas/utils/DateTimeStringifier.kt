package com.livmas.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.format.DateFormat
import android.text.style.ForegroundColorSpan
import java.util.Calendar

class DateTimeStringifier(var secondColor: Int = Color.LTGRAY) {
    fun stringifyDate(date: Calendar): SpannableStringBuilder {
        val secondString = DateFormat.format(", EE ", date)
        val secondSpannable = SpannableString(secondString)
        secondSpannable.setSpan(ForegroundColorSpan(secondColor), 0, secondSpannable.length, 0)

        return SpannableStringBuilder()
            .append(date[Calendar.DAY_OF_MONTH].toString())
            .append(' ')
            .append(DateFormat.format("MMM", date).slice(0..2))
            .append(secondSpannable)
    }

    fun stringifyTime(c: Calendar): String {
        return DateFormat.format("HH:mm", c).toString()
    }
}