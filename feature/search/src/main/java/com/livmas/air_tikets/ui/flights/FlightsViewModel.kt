package com.livmas.air_tikets.ui.flights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Calendar

internal class FlightsViewModel : ViewModel() {
    val flightDate: MutableLiveData<Calendar> by lazy {
        MutableLiveData(Calendar.getInstance())
    }

    val returnFlightDate: MutableLiveData<Calendar> by lazy {
        MutableLiveData(null)
    }
}