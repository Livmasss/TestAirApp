package com.livmas.search.ui.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.search.domain.usecases.GetTicketsUseCase
import com.livmas.search.ui.mappers.SearchMapper
import com.livmas.search.ui.tickets.adapter.TicketModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class TicketsViewModel(
    private val getTicketsUseCase: GetTicketsUseCase
) : ViewModel() {
    val tickets: LiveData<List<TicketModel>?>
        get() = _tickets
    private val _tickets by lazy {
        MutableLiveData<List<TicketModel>>(null)
    }

    fun refreshTickets() {
        CoroutineScope(Dispatchers.IO).launch {
            val tickets = getTicketsUseCase.execute().map {
                SearchMapper.ticketsDTOToUiModel(it)
            }.orderTickets()
            _tickets.postValue(tickets)
        }
    }

    private fun List<TicketModel>.orderTickets(): List<TicketModel> =
        sortedWith(
            compareByDescending<TicketModel> {
                nullableToInt(it.badge)
            }.thenBy {
                it.price
            }
        )

    private fun nullableToInt(value: Any?): Int {
        return if (value == null)
            0
        else
            1
    }
}