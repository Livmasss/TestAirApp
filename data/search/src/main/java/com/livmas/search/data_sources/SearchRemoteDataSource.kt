package com.livmas.search.data_sources

import com.livmas.search.apis.SearchAPI
import com.livmas.search.models.GetFeedResponseBody
import com.livmas.search.models.GetFlightsResponseBody
import com.livmas.search.models.GetTicketsResponseBody
import retrofit2.Response

internal class SearchRemoteDataSource(
    private val api: SearchAPI
) {
    suspend fun getFeed(): GetFeedResponseBody =
        api.getFeed().unwrapBodyNotNull()

    suspend fun getFlights(): GetFlightsResponseBody =
        api.getFlights().unwrapBodyNotNull()

    suspend fun getTickets(): GetTicketsResponseBody =
        api.getTickets().unwrapBodyNotNull()

    private fun <T> Response<T>.unwrapBody(): T? {
        if (!isSuccessful)
            throw Exception("Data fetching failed with code: ${code()}")

        return body()
    }

    private fun <T> Response<T>.unwrapBodyNotNull(): T {
        val bodyNullable = unwrapBody()
        return bodyNullable ?: throw Exception("Response body is null")
    }
}