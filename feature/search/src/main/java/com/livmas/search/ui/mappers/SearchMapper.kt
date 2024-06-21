package com.livmas.search.ui.mappers

import com.livmas.search.domain.dtos.FeedElementDTO
import com.livmas.search.domain.dtos.FlightDTO
import com.livmas.search.domain.dtos.TicketDTO
import com.livmas.search.ui.flights.adapter.FlightModel
import com.livmas.search.ui.home.music_adapter.FeedItemModel
import com.livmas.search.ui.tickets.adapter.TicketModel

internal object SearchMapper {
    fun feedItemDTOToUiModel(dto: FeedElementDTO) =
        FeedItemModel(
            dto.id,
            dto.title,
            dto.town,
            dto.price
        )

    fun flightsDTOToUiModel(dto: FlightDTO) = FlightModel(
        dto.flightId,
        dto.company,
        dto.price,
        dto.times
    )

    fun ticketsDTOToUiModel(it: TicketDTO): TicketModel =
        TicketModel(
            it.price,

            it.startTime,
            it.endTime,

            it.endCity,
            it.startCity,

            it.timeInTrip.toFloat(),
            it.hasTransfer,
            it.badge
        )
}