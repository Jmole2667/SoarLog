package com.soarlog.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.soarlog.app.models.Flight
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(flights: List<Flight>) {
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
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Total Flights: ${flights.size}")
                    Text(text = "Total Duration: ${flights.sumOf { it.duration }} minutes")

                    val flightsByGlider = flights.groupBy { it.gliderType }
                    flightsByGlider.forEach { (gliderType, flights) ->
                        Text(text = "Flights with $gliderType: ${flights.size}")
                        Text(text = "Total duration with $gliderType: ${flights.sumOf { it.duration }} minutes")
                    }
                }
            }
        }
    }
}
