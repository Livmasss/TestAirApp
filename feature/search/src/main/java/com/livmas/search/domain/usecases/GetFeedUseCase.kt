package com.livmas.search.domain.usecases

import com.livmas.search.domain.repositories.SearchRepository

internal class GetFeedUseCase(
    private val repository: SearchRepository
) {
    suspend fun execute() =
        repository.getFeed()
}