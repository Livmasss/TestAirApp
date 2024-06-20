package com.livmas.search.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.search.domain.usecases.SaveStartCityUseCase
import com.livmas.utils.LogTags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class SearchViewModel(
    private val readCityUseCase: com.livmas.search.domain.usecases.ReadStartCityUseCase,
    private val saveCityUseCase: SaveStartCityUseCase,
) : ViewModel() {
    val destination: MutableLiveData<String> by lazy {
        MutableLiveData(null)
    }

    val _startCity: MutableLiveData<String> by lazy {
        MutableLiveData(null)
    }

    val startCity: LiveData<String>
        get() = _startCity

    val initialStartCity: LiveData<String>
        get() = _initialStartCity
    private val _initialStartCity: MutableLiveData<String> by lazy {
        MutableLiveData(null)
    }

    val flightDate: MutableLiveData<Calendar> by lazy {
        MutableLiveData(Calendar.getInstance())
    }

    val returnFlightDate: MutableLiveData<Calendar> by lazy {
        MutableLiveData(null)
    }

    val passengersCount: MutableLiveData<Int> by lazy {
        MutableLiveData(0)
    }

    fun postStartCity(value: String?) {
        _initialStartCity.postValue(value)
        _startCity.postValue(value)
    }

    fun saveStartCity() {
        CoroutineScope(Dispatchers.IO).launch {
            _startCity.value?.let { saveCityUseCase.execute(it) }
        }
        Log.d(LogTags.IO_LOCAL, "Start city saved: ${_startCity.value}")
    }

    fun readStartCity() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = readCityUseCase.execute()
            _initialStartCity.postValue(result)
        }
        Log.d(LogTags.IO_LOCAL, "Start city read: ${_startCity.value}")
    }

    fun swapFromAndTo() {
        val temp = destination.value
        destination.postValue(_startCity.value)
        postStartCity(temp)
    }
}