package com.soarlog.app.repository

import com.soarlog.app.data.FlightDao
import com.soarlog.app.models.Flight
import com.soarlog.app.models.OgnFlight
import com.soarlog.app.network.OgnApiService
import kotlinx.coroutines.flow.Flow

class FlightRepository(
    private val apiService: OgnApiService,
    private val flightDao: FlightDao
) {
    suspend fun getFlights(registration: String): List<OgnFlight> {
        return apiService.getFlights(registration)
    }

    fun getAllFlights(): Flow<List<Flight>> {
        return flightDao.getAllFlights()
    }

    suspend fun insert(flight: Flight) {
        flightDao.insert(flight)
    }

    suspend fun delete(flight: Flight) {
        flightDao.delete(flight)
    }
}
