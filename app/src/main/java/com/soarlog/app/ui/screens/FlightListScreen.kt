package com.soarlog.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp
import com.soarlog.app.models.Flight
import com.soarlog.app.repository.FakeFlightRepository
import com.soarlog.app.repository.FlightRepository
import com.soarlog.app.viewmodel.FlightLogViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightListScreen(flights: List<Flight>, viewModel: FlightLogViewModel) {
    var sortOrder by remember { mutableStateOf(SortOrder.Date) }
    val sortedFlights = when (sortOrder) {
        SortOrder.Date -> flights.sortedByDescending { it.date }
        SortOrder.Duration -> flights.sortedByDescending { it.duration }
        SortOrder.GliderType -> flights.sortedBy { it.gliderType }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Flights") },
                actions = {
                    SortMenu(onSortOrderChange = { sortOrder = it })
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(sortedFlights) { flight ->
                FlightListItem(flight, onDelete = { viewModel.deleteFlight(flight) })
            }
        }
    }
}

@Composable
fun SortMenu(onSortOrderChange: (SortOrder) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Sort")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Sort by Date") },
                onClick = {
                    onSortOrderChange(SortOrder.Date)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Sort by Duration") },
                onClick = {
                    onSortOrderChange(SortOrder.Duration)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Sort by Glider Type") },
                onClick = {
                    onSortOrderChange(SortOrder.GliderType)
                    expanded = false
                }
            )
        }
    }
}

enum class SortOrder {
    Date,
    Duration,
    GliderType
}

@Composable
fun FlightListItem(flight: Flight, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Registration: ${flight.registration}")
            Text(text = "P2: ${flight.p2}")
            Text(text = "Notes: ${flight.notes}")
            Text(text = "Glider Type: ${flight.gliderType}")
            Text(text = "Takeoff: ${flight.takeoff}")
            Text(text = "Landing: ${flight.landing}")
            Text(text = "Launch Type: ${flight.launchType}")
            Text(text = "Duration: ${flight.duration} minutes")
            Text(text = "Date: ${flight.date}")
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun FlightListScreenPreview() {
    val flights = listOf(
        Flight(1, "G-ABCD", null, null, "K21", "Lasham", "Lasham", "Winch", 60, Date()),
        Flight(2, "G-EFGH", null, null, "Astir", "Lasham", "Lasham", "Aerotow", 120, Date())
    )
    val repository = FakeFlightRepository(null, null)
    FlightListScreen(flights, viewModel = object : FlightLogViewModel(repository) {
        override fun deleteFlight(flight: Flight) {}
    })
}
