package com.soarlog.app.viewmodel

import android.util.Log
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

open class FlightLogViewModel(private val repository: FlightRepository) : ViewModel() {

    private val _ognFlights = MutableStateFlow<List<OgnFlight>>(emptyList())
    val ognFlights: StateFlow<List<OgnFlight>> = _ognFlights

    val allFlights: StateFlow<List<Flight>> = repository.getAllFlights()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun searchFlights(registration: String) {
        viewModelScope.launch {
            try {
                _ognFlights.value = repository.getFlights(registration)
            } catch (e: Exception) {
                Log.e("FlightLogViewModel", "Error during searchFlights", e)
                _ognFlights.value = emptyList()
            }
        }
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
