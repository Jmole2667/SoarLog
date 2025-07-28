package com.soarlog.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soarlog.app.models.Flight
import com.soarlog.app.ui.charts.FlightsByMonthChart
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(flights: List<Flight>) {
    val years = flights.map {
        val calendar = Calendar.getInstance()
        calendar.time = it.date
        calendar.get(Calendar.YEAR)
    }.distinct().sortedDescending()

    var selectedYear by remember { mutableStateOf(years.firstOrNull()) }

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
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedYear != null) {
                YearSelector(
                    years = years,
                    selectedYear = selectedYear!!,
                    onYearSelected = { selectedYear = it }
                )
                FlightsByMonthChart(flights = flights, year = selectedYear!!)
            } else {
                Text("No flights recorded yet.")
            }
        }
    }
}

@Composable
fun YearSelector(
    years: List<Int>,
    selectedYear: Int,
    onYearSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        OutlinedTextField(
            value = selectedYear.toString(),
            onValueChange = {},
            readOnly = true,
            label = { Text("Year") },
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            years.forEach { year ->
                DropdownMenuItem(
                    text = { Text(year.toString()) }, // Pass the Text composable to the 'text' parameter
                    onClick = {
                        onYearSelected(year)
                        expanded = false
                    }
                    // You can add other parameters here if needed, like leadingIcon, etc.
                )
            }
            }
        }
    }
