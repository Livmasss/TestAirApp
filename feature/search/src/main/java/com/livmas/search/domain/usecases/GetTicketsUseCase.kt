package com.livmas.search.domain.usecases

import com.livmas.search.domain.repositories.SearchRepository

internal class GetTicketsUseCase(
    private val repository: SearchRepository
) {
    suspend fun execute() =
        repository.getTickets()
}