package com.livmas.search.apis

import com.livmas.search.models.GetFeedResponseBody
import com.livmas.search.models.GetFlightsResponseBody
import com.livmas.search.models.GetTicketsResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface SearchAPI {
    @GET("ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getFeed(): Response<GetFeedResponseBody>

    @GET("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getFlights(): Response<GetFlightsResponseBody>

    @GET("c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTickets(): Response<GetTicketsResponseBody>
}