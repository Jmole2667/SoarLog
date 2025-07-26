package com.soarlog.app.network

import com.soarlog.app.models.OgnFlight
import retrofit2.http.GET
import retrofit2.http.Path

interface OgnApiService {
    @GET("api/flights/{registration}")
    suspend fun getFlights(@Path("registration") registration: String): List<OgnFlight>
}
