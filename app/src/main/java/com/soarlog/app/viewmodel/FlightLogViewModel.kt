
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

    private val _allOgnFlights = MutableStateFlow<List<OgnFlightDisplay>>(emptyList())
    private val _registrationFilter = MutableStateFlow("")
    val registrationFilter: StateFlow<String> = _registrationFilter

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    val allFlights: StateFlow<List<Flight>> = repository.getAllFlights()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Search method for ICAO codes
    fun searchFlightsByAirfield(icaoCode: String, date: Date = Date()) {
        val cleanIcao = icaoCode.trim().uppercase()

        if (cleanIcao.isEmpty()) {
            _ognFlights.value = emptyList()
            _allOgnFlights.value = emptyList()
            _isSearching.value = false
            return
        }

        viewModelScope.launch {
            _isSearching.value = true
            val dateString = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)

            try {
                println("🔍 ICAO search: code=$cleanIcao, date=$dateString")
                val response = repository.getFlightsByAirfield(cleanIcao, date)
                println("📊 ICAO API Response: flights=${response.flights?.size}")

                val airfieldFlights = response.flights?.mapNotNull { flight ->
                    val device = response.devices?.getOrNull(flight.device)

                    // Skip flights without start/stop times
                    if (flight.start == null || flight.stop == null) {
                        return@mapNotNull null
                    }

                    OgnFlightDisplay(
                        registration = device?.registration ?: "Unknown",
                        aircraftModel = device?.aircraft ?: "Unknown",
                        takeoffTime = flight.start,
                        landingTime = flight.stop,
                        duration = flight.duration ?: 0,
                        maxAltitude = flight.maxAlt ?: 0,
                        takeoffAirfield = response.airfield?.code ?: "Unknown",
                        landingAirfield = response.airfield?.code ?: "Unknown",
                        launchMethod = if (flight.towing == true) "Tow" else "Unknown",
                        takeoffTs = parseOgnTimeToSeconds(flight.start),
                        landingTs = parseOgnTimeToSeconds(flight.stop)
                    )
                } ?: emptyList()

                // Sort by takeoff time
                val sortedFlights = airfieldFlights.sortedBy { it.takeoffTs }

                _allOgnFlights.value = sortedFlights
                applyRegistrationFilter()
                _isSearching.value = false
                println("✅ Found ${sortedFlights.size} flights from ICAO search")

            } catch (e: retrofit2.HttpException) {
                println("❌ HTTP Error ${e.code()}: ${e.message()}")
                _ognFlights.value = emptyList()
                _allOgnFlights.value = emptyList()
                _isSearching.value = false
            } catch (e: Exception) {
                println("❌ Unexpected error: ${e.javaClass.simpleName}: ${e.message}")
                e.printStackTrace()
                _ognFlights.value = emptyList()
                _allOgnFlights.value = emptyList()
                _isSearching.value = false
            }
        }
    }

    fun filterByRegistration(registration: String) {
        _registrationFilter.value = registration.trim().uppercase()
        applyRegistrationFilter()
    }

    private fun applyRegistrationFilter() {
        val filter = _registrationFilter.value
        _ognFlights.value = if (filter.isEmpty()) {
            _allOgnFlights.value
        } else {
            _allOgnFlights.value.filter { flight ->
                flight.registration.contains(filter, ignoreCase = true)
            }
        }
    }

    fun clearRegistrationFilter() {
        _registrationFilter.value = ""
        applyRegistrationFilter()
    }

    private fun parseOgnTimeToSeconds(timeString: String): Long {
        // Parse OGN time format like "09h34" or "10h05"
        val regex = Regex("""(\d+)h(\d+)""")
        val matchResult = regex.find(timeString)

        return if (matchResult != null) {
            val hours = matchResult.groupValues[1].toLongOrNull() ?: 0L
            val minutes = matchResult.groupValues[2].toLongOrNull() ?: 0L
            hours * 3600 + minutes * 60
        } else {
            0L
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
        _allOgnFlights.value = emptyList()
        _registrationFilter.value = ""
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