package com.livmas.air_tikets.domain.usecases

import com.livmas.air_tikets.domain.repositories.CityRepository

class ReadStartCityUseCase(
    private val repository: CityRepository
) {
    suspend fun execute() =
        repository.readStartCity()
}