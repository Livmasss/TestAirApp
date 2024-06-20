package com.livmas.search.repositories

import com.livmas.search.data_sources.SearchRemoteDataSource
import com.livmas.search.domain.dtos.FeedElementDTO
import com.livmas.search.domain.dtos.TicketDTO
import com.livmas.search.domain.repositories.SearchRepository
import com.livmas.search.mappers.SearchMapper.feedResponseToDtoList
import com.livmas.search.mappers.SearchMapper.flightsResponseToDto
import com.livmas.search.mappers.SearchMapper.ticketsResponseToDto

internal class SearchRepositoryImpl(
    private val remoteDataSource: SearchRemoteDataSource
): SearchRepository {
    override suspend fun getFeed(): List<FeedElementDTO> =
        feedResponseToDtoList(
            remoteDataSource.getFeed()
        )

    override suspend fun getFlights() =
        flightsResponseToDto(
            remoteDataSource.getFlights()
        )

    override suspend fun getTickets(): List<TicketDTO> =
        ticketsResponseToDto(
            remoteDataSource.getTickets()
        )
}