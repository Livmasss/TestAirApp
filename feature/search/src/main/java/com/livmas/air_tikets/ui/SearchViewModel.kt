package com.livmas.air_tikets.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.air_tikets.domain.usecases.ReadStartCityUseCase
import com.livmas.air_tikets.domain.usecases.SaveStartCityUseCase
import com.livmas.utils.LogTags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val readCityUseCase: ReadStartCityUseCase,
    private val saveCityUseCase: SaveStartCityUseCase,
) : ViewModel() {
    fun saveStartCity() {
        CoroutineScope(Dispatchers.IO).launch {
            startCity.value?.let { saveCityUseCase.execute(it) }
        }
        Log.d(LogTags.IO_LOCAL, "Start city saved: ${startCity.value}")
    }

    fun readStartCity() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = readCityUseCase.execute()
            _initialStartCity.postValue(result)
        }
        Log.d(LogTags.IO_LOCAL, "Start city read: ${startCity.value}")
    }
    val destination: MutableLiveData<String> by lazy {
        MutableLiveData(null)
    }

    val startCity: MutableLiveData<String> by lazy {
        MutableLiveData(null)
    }

    val initialStartCity: LiveData<String>
        get() = _initialStartCity
    private val _initialStartCity: MutableLiveData<String> by lazy {
        MutableLiveData("")
    }
}