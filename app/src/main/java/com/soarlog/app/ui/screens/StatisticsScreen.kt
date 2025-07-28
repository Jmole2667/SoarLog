package com.soarlog.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
    val longestFlight = flights.maxByOrNull { it.duration }
    val mostFrequentAircraft = flights.groupBy { it.aircraftType }
        .maxByOrNull { it.value.size }?.key

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Statistics") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                StatisticCard("Total Flights", totalFlights.toString())
            }
            item {
                StatisticCard("Total Duration", "$totalDuration minutes")
            }
            item {
                StatisticCard("Average Duration", "$averageDuration minutes")
            }
            item {
                StatisticCard("Longest Flight", longestFlight?.duration?.toString()?.let { "$it minutes" } ?: "N/A")
            }
            item {
                StatisticCard("Most Frequent Aircraft", mostFrequentAircraft ?: "N/A")
            }
        }
    }
}

@Composable
fun StatisticCard(title: String, value: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
