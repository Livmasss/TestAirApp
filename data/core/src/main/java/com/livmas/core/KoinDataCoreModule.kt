package com.livmas.core

import com.livmas.air_tikets.domain.repositories.CityRepository
import com.livmas.core.datasources.LocalCityDataSource
import com.livmas.core.repositories.CityRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataCoreModule = module {
    singleOf(::LocalCityDataSource)
    single<CityRepository>{
        CityRepositoryImpl(get())
    }
}