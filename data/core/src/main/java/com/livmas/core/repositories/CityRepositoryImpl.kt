package com.livmas.core.repositories

import com.livmas.search.domain.repositories.CityRepository
import com.livmas.core.datasources.LocalCityDataSource

internal class CityRepositoryImpl(
    private val dataSource: LocalCityDataSource
): com.livmas.search.domain.repositories.CityRepository {
    override suspend fun saveStartCity(cityName: String) {
        dataSource.saveStartCity(cityName)
    }

    override suspend fun readStartCity(): String {
        return dataSource.readStartCity()
    }
}