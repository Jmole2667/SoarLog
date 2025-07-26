package com.soarlog.app.repository

import com.soarlog.app.data.FlightDao
import com.soarlog.app.models.Flight
import com.soarlog.app.network.OgnApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeFlightRepository(
    apiService: OgnApiService?,
    flightDao: FlightDao?
) : FlightRepository(apiService!!, flightDao!!) {
    override suspend fun getFlights(registration: String): List<com.soarlog.app.models.OgnFlight> {
        return emptyList()
    }

    override fun getAllFlights(): Flow<List<Flight>> {
        return flowOf(
            listOf(
                Flight(1, "G-ABCD", null, null, "K21", "Lasham", "Lasham", "Winch", 60, java.util.Date()),
                Flight(2, "G-EFGH", null, null, "Astir", "Lasham", "Lasham", "Aerotow", 120, java.util.Date())
            )
        )
    }

    override suspend fun insert(flight: Flight) {
        // Do nothing
    }

    override suspend fun delete(flight: Flight) {
        // Do nothing
    }
}
