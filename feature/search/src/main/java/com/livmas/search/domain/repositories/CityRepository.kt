package com.livmas.search.domain.repositories

interface CityRepository {
    suspend fun saveStartCity(cityName: String)
    suspend fun readStartCity(): String
}