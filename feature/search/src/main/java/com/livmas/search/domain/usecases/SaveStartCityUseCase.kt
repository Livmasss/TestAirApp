package com.livmas.search.domain.usecases

import com.livmas.search.domain.repositories.CityRepository

class SaveStartCityUseCase(
    private val repository: CityRepository
) {
    suspend fun execute(cityName: String) {
        repository.saveStartCity(cityName)
    }
}