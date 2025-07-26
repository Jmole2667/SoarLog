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
            Text(text = "Total Flights: ${flights.size}")
            Text(text = "Total Duration: ${flights.sumOf { it.duration }} minutes")
        }
    }
}
