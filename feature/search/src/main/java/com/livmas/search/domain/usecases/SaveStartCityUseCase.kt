package com.livmas.search.domain.usecases

import com.livmas.search.domain.repositories.CityRepository

class SaveStartCityUseCase(
    private val repository: com.livmas.search.domain.repositories.CityRepository
) {
    suspend fun execute(cityName: String) {
        repository.saveStartCity(cityName)
    }
}