package com.soarlog.app.network

import com.soarlog.app.models.OgnFlightResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OgnApiService {
    @GET("api/logbook/{airfield}/{date}")
    suspend fun getFlightsByAirfield(
        @Path("airfield") airfield: String,
        @Path("date") date: String
    ): Response<OgnFlightResponse>
}