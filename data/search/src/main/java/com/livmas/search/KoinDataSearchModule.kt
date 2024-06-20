package com.livmas.search

import com.livmas.air_tikets.domain.repositories.SearchRepository
import com.livmas.core.RetrofitConfig
import com.livmas.search.apis.SearchAPI
import com.livmas.search.data_sources.SearchRemoteDataSource
import com.livmas.search.repositories.SearchRepositoryImpl
import org.koin.dsl.module

val dataSearchModule = module {
    single {
        RetrofitConfig.createApi(SearchAPI::class.java)
    }

    single<SearchRepository> {
        SearchRepositoryImpl(get())
    }
    single {
        SearchRemoteDataSource(get())
    }
}