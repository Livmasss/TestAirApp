package com.livmas.search.domain.dtos

import java.util.Calendar

data class TicketDTO(
    val id: Long,
    val price: Int,

    val startTime: Calendar,
    val endTime: Calendar,

    val startCity: String,
    val endCity: String,

    val timeInTrip: Double,
    val hasTransfer: Boolean,

    val badge: String? = null
)

