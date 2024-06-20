package com.livmas.search.models

import com.google.gson.annotations.SerializedName

data class GetFlightsResponseBody(
    @SerializedName("tickets_offers")
    val ticketsOffers: List<FlightModel>
) {
    data class FlightModel(
        val id: Long,
        val title: String,
        @SerializedName("time_range")
        val timeRange: List<String>,
        val price: PriceModel
    )
}