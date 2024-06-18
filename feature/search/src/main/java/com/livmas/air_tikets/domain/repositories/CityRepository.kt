package com.livmas.air_tikets.domain.repositories

interface CityRepository {
    suspend fun saveStartCity(cityName: String)
    suspend fun readStartCity(): String
}