package com.livmas.search.domain.dtos

import java.util.Calendar

data class FlightDTO(
    val flightId: Long,
    val company: String,
    val price: Int,
    val times: List<Calendar>
)
