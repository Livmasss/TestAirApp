package com.livmas.search.ui.flights.adapter

import java.util.Calendar

internal data class FlightModel(
    val flightId: Int,
    val company: String,
    val price: Int,
    val times: List<Calendar>
)
