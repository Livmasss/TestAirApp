package com.livmas.search.mappers

import android.annotation.SuppressLint
import com.livmas.search.domain.dtos.FeedElementDTO
import com.livmas.search.domain.dtos.FlightDTO
import com.livmas.search.domain.dtos.TicketDTO
import com.livmas.search.models.GetFeedResponseBody
import com.livmas.search.models.GetFlightsResponseBody
import com.livmas.search.models.GetTicketsResponseBody
import java.text.SimpleDateFormat
import java.util.Calendar

object SearchMapper {

    @SuppressLint("SimpleDateFormat")
    val simpleTimeFormat = SimpleDateFormat("HH:mm")

    fun feedResponseToDtoList(model: GetFeedResponseBody): List<FeedElementDTO> =
        model.offers.map {
            FeedElementDTO(
                it.id,
                it.title,
                it.town,
                it.price.value
            )
        }

    fun flightsResponseToDto(
        model: GetFlightsResponseBody
    ): List<FlightDTO> =
        model.ticketsOffers.map {
            FlightDTO(
                it.id,
                company = it.title,
                price = it.price.value,
                times = it.timeRange.mapNotNull { stringTime ->
                    try {
                        val date = simpleTimeFormat.parse(stringTime) ?: throw Exception()
                        Calendar.getInstance().run {
                            time = date
                            this
                        }
                    } catch (e: Exception) {
                        null
                    }
                }
            )
        }

    fun ticketsResponseToDto(
        model: GetTicketsResponseBody
    ): List<TicketDTO> =
        model.tickets.map {
            val departureDate = Calendar.getInstance().run{
                time = it.departure.date
                this
            }
            val arrivalDate = Calendar.getInstance().run{
                time = it.arrival.date
                this
            }
            val timeInTripCalendar = Calendar.getInstance().run {
                timeInMillis = arrivalDate.timeInMillis - departureDate.timeInMillis
                this
            }
            val timeInTripInterval = timeInTripCalendar.run {
                get(Calendar.HOUR_OF_DAY) + get(Calendar.MINUTE) / 60f
            }

            TicketDTO(
                id = it.id,
                price = it.price.value,
                startTime = departureDate,
                endTime = arrivalDate,
                startCity = it.departure.town,
                endCity = it.arrival.town,
                timeInTrip = timeInTripInterval,
                hasTransfer = it.hasTransfer,
                badge = it.badge
            )
        }
}