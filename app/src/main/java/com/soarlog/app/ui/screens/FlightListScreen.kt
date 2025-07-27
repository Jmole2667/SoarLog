package com.soarlog.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp
import com.soarlog.app.models.Flight
import com.soarlog.app.repository.FakeFlightRepository
import com.soarlog.app.repository.FlightRepository
import com.soarlog.app.viewmodel.FlightLogViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
            items(sortedFlights.withIndex().toList()) { (index, flight) ->
                FlightListItem(flight, onDelete = { viewModel.deleteFlight(flight) }, flightNumber = index + 1)
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
fun FlightListItem(flight: Flight, onDelete: () -> Unit, flightNumber: Int) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$flightNumber.",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 16.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = flight.registration,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(flight.date))
                }
                Spacer(modifier = Modifier.height(8.dp))

                flight.gliderType?.let { if (it.isNotBlank()) Text(text = "Type: $it") }
                flight.takeoff?.let { if (it.isNotBlank()) Text(text = "Takeoff: $it") }
                flight.landing?.let { if (it.isNotBlank()) Text(text = "Landing: $it") }
                flight.launchType?.let { if (it.isNotBlank()) Text(text = "Launch Type: $it") }
                if (flight.duration > 0) Text(text = "Duration: ${flight.duration} minutes")
                flight.notes?.let { if (it.isNotBlank()) Text(text = "Notes: $it") }
            }
            IconButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Delete Flight") },
            text = { Text("Are you sure you want to delete this flight log?") },
            confirmButton = {
                Button(
                    onClick = {
                        onDelete()
                        showDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
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
