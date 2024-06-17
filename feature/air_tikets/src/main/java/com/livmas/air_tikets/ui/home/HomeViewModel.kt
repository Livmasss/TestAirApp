package com.livmas.air_tikets.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    fun saveStartCity() {
        Log.d("save", "Start city saved: ${startCity.value}")
    }

    val destination: MutableLiveData<String> by lazy {
        MutableLiveData("")
    }

    val startCity: MutableLiveData<String> by lazy {
        MutableLiveData("")
    }
}