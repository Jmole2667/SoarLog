package com.soarlog.app.repository

import com.soarlog.app.data.FlightDao
import com.soarlog.app.models.Flight
import com.soarlog.app.models.OgnFlight
import com.soarlog.app.models.OgnFlightResponse
import com.soarlog.app.models.OgnDevice
import com.soarlog.app.network.OgnApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

open class FlightRepository(
    private val apiService: OgnApiService,
    private val flightDao: FlightDao
) {
    // Method to search by a specific airfield
    open suspend fun getFlightsByAirfield(airfield: String, date: Date): OgnFlightResponse {
        val cleanAirfield = airfield.trim().uppercase()
        val dateString = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)

        println("🔍 Single airfield search: '$cleanAirfield' on $dateString")

        return try {
            val response = apiService.getFlightsByAirfield(cleanAirfield, dateString)
            
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                println("📊 $cleanAirfield: ${data.devices?.size} devices, ${data.flights?.size} flights")
                data
            } else {
                println("❌ Failed to search $cleanAirfield: ${response.code()}")
                OgnFlightResponse(null, null, emptyList())
            }
        } catch (e: HttpException) {
            println("❌ HTTP Error searching $cleanAirfield: ${e.code()}")
            OgnFlightResponse(null, null, emptyList())
        } catch (e: Exception) {
            println("❌ Error searching $cleanAirfield: ${e.message}")
            e.printStackTrace()
            OgnFlightResponse(null, null, emptyList())
        }
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