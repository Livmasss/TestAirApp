package com.livmas.search

import kotlinx.coroutines.test.runTest
import org.junit.BeforeClass
import org.junit.Test
import org.koin.core.component.inject
import org.koin.test.KoinTest
import kotlin.test.assertEquals

class SearchRepositoryTest: KoinTest {
    private val repository: com.livmas.search.domain.repositories.SearchRepository by inject()

    @Test
    fun getFeedSuccessTest() = runTest {
        assertEquals(3, repository.getFeed().size)
    }

    @Test
    fun getFlightsSuccessTest() = runTest {
        assertEquals(3, repository.getFlights().size)
    }

    @Test
    fun getTicketsSuccessTest() = runTest {
        assertEquals(7, repository.getTickets().size)
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun startKoin() {
            org.koin.core.context.startKoin {
                modules(
                    dataSearchModule
                )
            }
        }
    }
}