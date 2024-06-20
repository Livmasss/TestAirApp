package com.livmas.search.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class GetTicketsResponseBody(
    val tickets: List<TicketModel>
) {
    data class TicketModel(
        val id: Long,
        val badge: String,
        val price: PriceModel,

        @SerializedName("provider_name")
        val providerName: String,
        val company: String,

        val departure: DestinationModel,
        val arrival: DestinationModel,

        @SerializedName("has_transfer")
        val hasTransfer: Boolean,
        @SerializedName("has_visa_transfer")
        val hasVisaTransfer: Boolean,

        val luggage: LuggageModel,
        @SerializedName("hand_luggage")
        val handLuggage: HandLuggageModel,

        @SerializedName("is_returnable")
        val isReturnable: Boolean,
        @SerializedName("is_exchangable")
        val isExchangable: Boolean,
    ) {
        data class DestinationModel(
            val town: String,
            val date: Date
        )

        data class LuggageModel(
            @SerializedName("has_luggage")
            val hasLuggage: Boolean,
            val price: PriceModel
        )
        data class HandLuggageModel(
            @SerializedName("has_hand_luggage")
            val hasHandLuggage: Boolean,
            val size: String
        )
    }
}
