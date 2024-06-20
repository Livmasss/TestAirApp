package com.livmas.search

import com.livmas.search.domain.usecases.ReadStartCityUseCase
import com.livmas.search.domain.usecases.SaveStartCityUseCase
import com.livmas.search.ui.SearchViewModel
import com.livmas.search.ui.flights.FlightsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::FlightsViewModel)
    singleOf(::SaveStartCityUseCase)
    singleOf(::ReadStartCityUseCase)
}