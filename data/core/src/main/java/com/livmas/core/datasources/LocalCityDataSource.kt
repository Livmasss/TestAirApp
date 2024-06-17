package com.livmas.core.datasources

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

internal class LocalCityDataSource(private val context: Context) {
    private val Context.dataStore by preferencesDataStore("cityDataStore")
    suspend fun saveStartCity(cityName: String) {
        context.dataStore.updateData {
            it.toMutablePreferences().apply {
                set(Keys.START_CITY, cityName)
            }
        }
    }

    suspend fun readStartCity(): String {
        val cities = context.dataStore.data.map {
            it[Keys.START_CITY]
        }

        return cities.firstOrNull()
            ?: throw IllegalAccessException("No start city written before.")
    }

    private object Keys {
        val START_CITY = stringPreferencesKey("startCity")
    }
}