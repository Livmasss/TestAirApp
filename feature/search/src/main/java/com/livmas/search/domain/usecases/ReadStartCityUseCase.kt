package com.livmas.search.domain.usecases

import com.livmas.search.domain.repositories.CityRepository

class ReadStartCityUseCase(
    private val repository: com.livmas.search.domain.repositories.CityRepository
) {
    suspend fun execute() =
        repository.readStartCity()
}