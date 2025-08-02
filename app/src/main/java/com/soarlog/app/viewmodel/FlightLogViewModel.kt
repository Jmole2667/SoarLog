package com.soarlog.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soarlog.app.models.Flight
import com.soarlog.app.models.OgnFlight
import com.soarlog.app.repository.FlightRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

open class FlightLogViewModel(private val repository: FlightRepository) : ViewModel() {

    private val _ognFlights = MutableStateFlow<List<OgnFlightDisplay>>(emptyList())
    val ognFlights: StateFlow<List<OgnFlightDisplay>> = _ognFlights

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    val allFlights: StateFlow<List<Flight>> = repository.getAllFlights()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun searchFlights(registration: String, date: Date = Date()) {
        val cleanRegistration = registration.trim().uppercase()

        if (cleanRegistration.isEmpty() || cleanRegistration.length < 2) {
            _ognFlights.value = emptyList()
            _isSearching.value = false
            return
        }

        viewModelScope.launch {
            _isSearching.value = true
            val dateString = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)

            try {
                println("🔍 Multi-airfield search: registration=$cleanRegistration, date=$dateString")

                val response = repository.getFlightResponse(cleanRegistration, date)
                println("📊 API Response: flights=${response.flights?.size}")

                val displayFlights = response.flights?.map { flight ->
                    // Get device info using array index instead of map key
                    val device = response.devices?.getOrNull(flight.device)

                    OgnFlightDisplay(
                        registration = device?.registration ?: cleanRegistration,
                        aircraftModel = device?.aircraft ?: "Unknown",
                        takeoffTime = flight.takeoffTime,
                        landingTime = flight.landingTime,
                        duration = flight.duration,
                        maxAltitude = flight.maxAltitude ?: 0,
                        takeoffAirfield = flight.takeoffAirfield?.shortName ?: flight.takeoffAirfield?.name ?: "Unknown",
                        landingAirfield = flight.landingAirfield?.shortName ?: flight.landingAirfield?.name ?: "Unknown",
                        launchMethod = flight.launchMethod ?: "Unknown",
                        takeoffTs = parseTimeToSeconds(flight.takeoffTime),
                        landingTs = parseTimeToSeconds(flight.landingTime)
                    )
                } ?: emptyList()

                println("✅ Found ${displayFlights.size} flights from multi-airfield search")
                _ognFlights.value = displayFlights
                _isSearching.value = false

            } catch (e: retrofit2.HttpException) {
                println("❌ HTTP Error ${e.code()}: ${e.message()}")
                _ognFlights.value = emptyList()
                _isSearching.value = false
            } catch (e: Exception) {
                println("❌ Unexpected error: ${e.javaClass.simpleName}: ${e.message}")
                e.printStackTrace()
                _ognFlights.value = emptyList()
                _isSearching.value = false
            }
        }
    }

    private fun parseTimeToSeconds(timeString: String): Long {
        val parts = timeString.split(":")
        if (parts.size >= 2) {
            val hours = parts[0].toIntOrNull() ?: 0
            val minutes = parts[1].toIntOrNull() ?: 0
            return (hours * 3600 + minutes * 60).toLong()
        }
        return 0L
    }

    fun clearSearch() {
        _ognFlights.value = emptyList()
        _isSearching.value = false
    }

    fun insertFlight(flight: Flight) {
        viewModelScope.launch {
            repository.insert(flight)
        }
    }

    open fun deleteFlight(flight: Flight) {
        viewModelScope.launch {
            repository.delete(flight)
        }
    }
}

// Display model for the UI
data class OgnFlightDisplay(
    val registration: String,
    val aircraftModel: String,
    val takeoffTime: String,
    val landingTime: String,
    val duration: Int,
    val maxAltitude: Int,
    val takeoffAirfield: String,
    val landingAirfield: String,
    val launchMethod: String,
    val takeoffTs: Long,
    val landingTs: Long
)