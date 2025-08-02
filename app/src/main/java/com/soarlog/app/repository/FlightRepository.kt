package com.soarlog.app.repository

import com.soarlog.app.data.FlightDao
import com.soarlog.app.models.Flight
import com.soarlog.app.models.OgnFlight
import com.soarlog.app.models.OgnFlightResponse
import com.soarlog.app.models.OgnDevice
import com.soarlog.app.network.OgnApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

open class FlightRepository(
    private val apiService: OgnApiService,
    private val flightDao: FlightDao
) {
    // List of common airfields to search (same as web app)
    private val airfieldsToSearch = listOf(
        "GB-0408", // Southdown
        "EGKA",    // Shoreham
        "GB-0001", // Lasham
        "LFDA",    // French airfield
        "EGTB"     // Booker
    )

    open suspend fun getFlights(registration: String): List<OgnFlight> {
        val response = getFlightResponse(registration, Date())
        return response.flights ?: emptyList()
    }

    open suspend fun getFlightResponse(registration: String, date: Date): OgnFlightResponse {
        val cleanRegistration = registration.trim().uppercase()
        val dateString = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)

        println("🔍 Multi-airfield search for: '$cleanRegistration' on $dateString")

        return try {
            coroutineScope {
                // Search all airfields simultaneously (like web app does)
                val searchTasks = airfieldsToSearch.map { airfield ->
                    async {
                        try {
                            println("📡 Searching airfield: $airfield")
                            val response = apiService.getFlightsByAirfield(airfield, dateString)

                            if (response.isSuccessful && response.body() != null) {
                                val data = response.body()!!
                                println("📊 $airfield: ${data.devices?.size} devices, ${data.flights?.size} flights")

                                // Filter flights by registration using array index
                                val matchingFlights = data.flights?.filter { flight ->
                                    val device = data.devices?.getOrNull(flight.device)
                                    val deviceReg = device?.registration
                                    println("🔍 Checking device ${flight.device}: $deviceReg")
                                    deviceReg?.contains(cleanRegistration, ignoreCase = true) == true
                                } ?: emptyList()

                                if (matchingFlights.isNotEmpty()) {
                                    println("✅ Found ${matchingFlights.size} flights for $cleanRegistration at $airfield")
                                    // Get the matching device
                                    val matchingDevice = data.devices?.getOrNull(matchingFlights[0].device)

                                    Triple(data.airfield, matchingDevice, matchingFlights)
                                } else {
                                    println("❌ No matching flights for $cleanRegistration at $airfield")
                                    null
                                }
                            } else {
                                println("❌ Failed to search $airfield: ${response.code()}")
                                null
                            }
                        } catch (e: Exception) {
                            println("❌ Error searching $airfield: ${e.message}")
                            e.printStackTrace()
                            null
                        }
                    }
                }

                // Wait for all searches to complete
                val results = searchTasks.awaitAll().filterNotNull()

                if (results.isNotEmpty()) {
                    // Combine all matching flights from all airfields
                    val allFlights = results.flatMap { it.third }
                    val firstResult = results.first()

                    println("✅ Total flights found across all airfields: ${allFlights.size}")

                    // Create a combined response with the first matching aircraft info
                    OgnFlightResponse(
                        airfield = firstResult.first,
                        devices = listOf(firstResult.second ?: OgnDevice(null, "Unknown", cleanRegistration)),
                        flights = allFlights
                    )
                } else {
                    println("❌ No flights found for $cleanRegistration across any airfield")
                    OgnFlightResponse(null, null, emptyList())
                }
            }
        } catch (e: Exception) {
            println("❌ Multi-airfield search failed: ${e.message}")
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