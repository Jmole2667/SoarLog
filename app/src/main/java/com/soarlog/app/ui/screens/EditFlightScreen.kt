package com.soarlog.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soarlog.app.models.Flight
import com.soarlog.app.viewmodel.FlightLogViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFlightScreen(
    flight: Flight,
    viewModel: FlightLogViewModel,
    onBack: () -> Unit
) {
    var registration by remember { mutableStateOf(flight.registration) }
    var p2 by remember { mutableStateOf(flight.p2 ?: "") }
    var notes by remember { mutableStateOf(flight.notes ?: "") }
    var gliderType by remember { mutableStateOf(flight.gliderType) }
    var takeoff by remember { mutableStateOf(flight.takeoff) }
    var landing by remember { mutableStateOf(flight.landing) }
    var launchType by remember { mutableStateOf(flight.launchType) }
    var duration by remember { mutableStateOf(flight.duration.toString()) }
    var selectedDate by remember { mutableStateOf(flight.date) }
    var showDatePicker by remember { mutableStateOf(false) }
    var takeoffTime by remember { mutableStateOf(flight.takeoffTime ?: "") }
    var landingTime by remember { mutableStateOf(flight.landingTime ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Flight") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = registration,
                onValueChange = { registration = it.uppercase() },
                label = { Text("Aircraft Registration") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            OutlinedTextField(
                value = p2,
                onValueChange = { p2 = it },
                label = { Text("P2 (optional)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            OutlinedTextField(
                value = gliderType,
                onValueChange = { gliderType = it },
                label = { Text("Glider Type") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            OutlinedTextField(
                value = takeoff,
                onValueChange = { takeoff = it },
                label = { Text("Takeoff") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            OutlinedTextField(
                value = landing,
                onValueChange = { landing = it },
                label = { Text("Landing") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            OutlinedTextField(
                value = launchType,
                onValueChange = { launchType = it },
                label = { Text("Launch Type") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            OutlinedTextField(
                value = duration,
                onValueChange = { duration = it },
                label = { Text("Duration (minutes)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            OutlinedTextField(
                value = takeoffTime,
                onValueChange = { takeoffTime = it },
                label = { Text("Takeoff Time") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            OutlinedTextField(
                value = landingTime,
                onValueChange = { landingTime = it },
                label = { Text("Landing Time") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            Button(
                onClick = { showDatePicker = true },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Select Date: ${SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate)}")
            }

            if (showDatePicker) {
                val datePickerState = rememberDatePickerState(
                    initialSelectedDateMillis = selectedDate.time
                )
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                selectedDate = datePickerState.selectedDateMillis?.let { Date(it) } ?: selectedDate
                                showDatePicker = false
                            }
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showDatePicker = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            Button(
                onClick = {
                    val updatedFlight = flight.copy(
                        registration = registration,
                        p2 = p2,
                        notes = notes,
                        gliderType = gliderType,
                        takeoff = takeoff,
                        landing = landing,
                        launchType = launchType,
                        duration = duration.toLongOrNull() ?: 0,
                        date = selectedDate,
                        takeoffTime = takeoffTime,
                        landingTime = landingTime
                    )
                    viewModel.updateFlight(updatedFlight)
                    onBack()
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Save Changes")
            }
        }
    }
}
