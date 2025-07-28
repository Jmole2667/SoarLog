package com.soarlog.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soarlog.app.models.Flight
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(flights: List<Flight>) {
    val totalFlights = flights.size
    val totalDuration = flights.sumOf { it.duration }
    val averageDuration = if (totalFlights > 0) totalDuration / totalFlights else 0
    val mostCommonGlider = flights.groupBy { it.gliderType }
        .maxByOrNull { it.value.size }?.key

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Statistics") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (flights.isEmpty()) {
                Text("No flights recorded yet.")
            } else {
                Row(modifier = Modifier.fillMaxWidth()) {
                    StatBox(title = "Total Flights", value = totalFlights.toString(), modifier = Modifier.weight(1f))
                    StatBox(title = "Total Flight Time", value = "$totalDuration min", modifier = Modifier.weight(1f))
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    StatBox(title = "Average Flight Time", value = "$averageDuration min", modifier = Modifier.weight(1f))
                    if (mostCommonGlider != null) {
                        StatBox(title = "Most Common Glider", value = mostCommonGlider, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun StatBox(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.headlineSmall)
            Text(text = value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
