package com.soarlog.app.repository

import com.soarlog.app.data.FlightDao
import com.soarlog.app.models.Flight
import com.soarlog.app.models.OgnFlight
import com.soarlog.app.network.OgnApiService
import kotlinx.coroutines.flow.Flow

open class FlightRepository(
    private val apiService: OgnApiService,
    private val flightDao: FlightDao
) {
    open suspend fun getFlights(registration: String): List<OgnFlight> {
        return apiService.getFlights(registration)
    }

    open fun getAllFlights(): Flow<List<Flight>> {
        return flightDao.getAllFlights()
    }

    open suspend fun insert(flight: Flight) {
        flightDao.insert(flight)
    }

    open suspend fun delete(flight: Flight) {
        flightDao.delete(flight)
    }
}
