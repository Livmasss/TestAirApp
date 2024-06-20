package com.livmas.search.domain.repositories

import com.livmas.search.domain.dtos.FeedElementDTO
import com.livmas.search.domain.dtos.FlightDTO
import com.livmas.search.domain.dtos.TicketDTO

interface SearchRepository {
    suspend fun getFeed(): List<com.livmas.search.domain.dtos.FeedElementDTO>
    suspend fun getFlights(): List<com.livmas.search.domain.dtos.FlightDTO>
    suspend fun getTickets(): List<com.livmas.search.domain.dtos.TicketDTO>
}