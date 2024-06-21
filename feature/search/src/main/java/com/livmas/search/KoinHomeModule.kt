package com.livmas.search

import com.livmas.search.domain.usecases.GetFeedUseCase
import com.livmas.search.domain.usecases.GetFlightsUseCase
import com.livmas.search.domain.usecases.GetTicketsUseCase
import com.livmas.search.domain.usecases.ReadStartCityUseCase
import com.livmas.search.domain.usecases.SaveStartCityUseCase
import com.livmas.search.ui.SearchViewModel
import com.livmas.search.ui.flights.FlightsViewModel
import com.livmas.search.ui.home.HomeViewModel
import com.livmas.search.ui.tickets.TicketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val featureSearchDiModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::FlightsViewModel)
    viewModelOf(::TicketsViewModel)
    viewModelOf(::HomeViewModel)

    singleOf(::SaveStartCityUseCase)
    singleOf(::ReadStartCityUseCase)
    singleOf(::GetFeedUseCase)
    singleOf(::GetFlightsUseCase)
    singleOf(::GetTicketsUseCase)
}