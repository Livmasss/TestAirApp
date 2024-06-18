package com.livmas.air_tikets

import com.livmas.air_tikets.domain.usecases.ReadStartCityUseCase
import com.livmas.air_tikets.domain.usecases.SaveStartCityUseCase
import com.livmas.air_tikets.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::SearchViewModel)
    singleOf(::SaveStartCityUseCase)
    singleOf(::ReadStartCityUseCase)
}