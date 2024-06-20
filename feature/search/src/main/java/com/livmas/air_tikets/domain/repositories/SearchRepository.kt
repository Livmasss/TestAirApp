package com.livmas.air_tikets.domain.repositories

import com.livmas.air_tikets.domain.dtos.FeedElementDTO
import com.livmas.air_tikets.domain.dtos.FlightDTO
import com.livmas.air_tikets.domain.dtos.TicketDTO

interface SearchRepository {
    suspend fun getFeed(): List<FeedElementDTO>
    suspend fun getFlights(): List<FlightDTO>
    suspend fun getTickets(): List<TicketDTO>
}