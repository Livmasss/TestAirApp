package com.livmas.air_tikets.domain.usecases

import com.livmas.air_tikets.domain.repositories.CityRepository

class SaveStartCityUseCase(
    private val repository: CityRepository
) {
    suspend fun execute(cityName: String) {
        repository.saveStartCity(cityName)
    }
}