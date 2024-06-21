package com.livmas.utils

import java.util.Calendar
import kotlin.math.round
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object CalendarUtil {
    fun hourDifference(c1: Calendar, c2: Calendar): Double {
        val firstMillis = c1.timeInMillis
        val secondMillis = c2.timeInMillis

        val resultMillis = firstMillis - secondMillis
        return resultMillis.toDuration(DurationUnit.MILLISECONDS).toDouble(DurationUnit.HOURS)

    }
    fun roundOffDecimal(number: Double): Double {
        val numberX10 = round(number * 10)
        return numberX10 / 10
    }
}