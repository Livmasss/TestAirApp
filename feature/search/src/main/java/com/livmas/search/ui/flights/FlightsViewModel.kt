package com.livmas.search.ui.flights

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.search.domain.usecases.GetFlightsUseCase
import com.livmas.search.ui.flights.adapter.FlightModel
import com.livmas.search.ui.mappers.SearchMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class FlightsViewModel(
    private val getFlightsUseCase: GetFlightsUseCase
) : ViewModel() {
    val flights: LiveData<List<FlightModel>?>
        get() = _flights
    private val _flights by lazy {
        MutableLiveData<List<FlightModel>>(null)
    }

    fun refreshFlights() {
        CoroutineScope(Dispatchers.IO).launch {
            val newFlights = getFlightsUseCase.execute()
            _flights.postValue(
                newFlights.map {
                    SearchMapper.flightsDTOToUiModel(
                        it
                    )
                }
            )
        }
    }
}