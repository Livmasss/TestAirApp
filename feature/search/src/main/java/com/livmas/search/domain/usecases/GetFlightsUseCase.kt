package com.livmas.search.domain.usecases

import com.livmas.search.domain.repositories.SearchRepository

internal class GetFlightsUseCase(
    private val repository: SearchRepository
) {
    suspend fun execute() =
        repository.getFlights().slice(0..2)
}