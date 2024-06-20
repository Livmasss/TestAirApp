package com.livmas.search.ui.tickets.adapter

import java.util.Calendar

data class TicketModel(
    val price: Int,

    val startTime: Calendar,
    val endTime: Calendar,

    val endCity: String,
    val startCity: String,

    val timeInTrip: Float,
    val hasTransfer: Boolean,

    val badge: String? = null
)
