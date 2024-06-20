package com.livmas.core

import com.livmas.search.domain.repositories.CityRepository
import com.livmas.core.datasources.LocalCityDataSource
import com.livmas.core.repositories.CityRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataCoreModule = module {
    singleOf(::LocalCityDataSource)
    single<com.livmas.search.domain.repositories.CityRepository>{
        CityRepositoryImpl(get())
    }
}