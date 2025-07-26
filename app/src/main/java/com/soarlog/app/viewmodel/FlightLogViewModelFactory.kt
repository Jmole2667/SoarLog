package com.soarlog.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soarlog.app.repository.FlightRepository

class FlightLogViewModelFactory(private val repository: FlightRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlightLogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlightLogViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
