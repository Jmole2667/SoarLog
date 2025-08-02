package com.soarlog.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.soarlog.app.data.AppDatabase
import com.soarlog.app.models.Flight
import com.soarlog.app.network.ApiClient
import com.soarlog.app.repository.FlightRepository
import com.soarlog.app.ui.theme.SoarLogTheme
import com.soarlog.app.viewmodel.FlightLogViewModel
import com.soarlog.app.viewmodel.FlightLogViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoarLogTheme {
                val navController = rememberNavController()
                val database = AppDatabase.getDatabase(applicationContext)
                val repository = FlightRepository(ApiClient.ognApiService, database.flightDao())
                val viewModel: FlightLogViewModel = viewModel(
                    factory = FlightLogViewModelFactory(repository)
                )

                NavHost(navController = navController, startDestination = "flightForm") {
                    composable("flightForm") {
                        FlightLogForm(
                            viewModel = viewModel,
                            onNavigateToFlightList = { navController.navigate("flightList") }
                        )
                    }
                    composable("flightList") {
                        FlightListScreen(
                            viewModel = viewModel,
                            onNavigateToForm = { navController.navigate("flightForm") }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightLogForm(
    viewModel: FlightLogViewModel,
    onNavigateToFlightList: () -> Unit
) {
    var flightRegistration by remember { mutableStateOf("") }
    var searchAirfield by remember { mutableStateOf("") }
    var p2 by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var gliderType by remember { mutableStateOf("") }
    var takeoff by remember { mutableStateOf("") }
    var landing by remember { mutableStateOf("") }
    var launchType by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<Date>(Date()) }
    var showDatePicker by remember { mutableStateOf(false) }
    var searchDate by remember { mutableStateOf<Date>(Date()) }
    var showSearchDatePicker by remember { mutableStateOf(false) }
    var lastSearchTerm by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SoarLog") },
                actions = {
                    IconButton(onClick = onNavigateToFlightList) {
                        Icon(Icons.Default.List, contentDescription = "View All Flights")
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
            Text(
                text = "Browse Flights by ICAO Code",
                style = MaterialTheme.typography.headlineMedium
            )

            val ognFlights by viewModel.ognFlights.collectAsState()
            val isSearching by viewModel.isSearching.collectAsState()
            val registrationFilter by viewModel.registrationFilter.collectAsState()

            // ICAO code search field
            OutlinedTextField(
                value = searchAirfield,
                onValueChange = { newValue ->
                    searchAirfield = newValue.uppercase()
                    val cleanIcao = newValue.trim().uppercase()
                    val currentSearchTerm = "$cleanIcao-${searchDate.time}"

                    if (cleanIcao.length >= 2 && currentSearchTerm != lastSearchTerm) {
                        println("✅ Triggering ICAO search: '$cleanIcao'")
                        lastSearchTerm = currentSearchTerm
                        viewModel.searchFlightsByAirfield(cleanIcao, searchDate)
                    } else if (cleanIcao.length < 2) {
                        lastSearchTerm = ""
                        viewModel.clearSearch()
                    }
                },
                label = { Text("ICAO Code") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                supportingText = {
                    Text("Examples: EGHL (Lasham), EGAD (Dunstable), EGYK (Sutton Bank), EGTB (Booker)")
                }
            )

            // Date selector button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Search Date:")
                TextButton(onClick = { showSearchDatePicker = true }) {
                    Text(searchDate.toFormattedString())
                }
            }

            // Registration filter - only show when flights are available
            if (ognFlights.isNotEmpty() || registrationFilter.isNotEmpty()) {
                OutlinedTextField(
                    value = registrationFilter,
                    onValueChange = { viewModel.filterByRegistration(it) },
                    label = { Text("Filter by Registration") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    singleLine = true,
                    trailingIcon = {
                        if (registrationFilter.isNotEmpty()) {
                            IconButton(onClick = { viewModel.clearRegistrationFilter() }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear filter")
                            }
                        }
                    },
                    supportingText = {
                        if (ognFlights.isNotEmpty()) {
                            Text("${ognFlights.size} of ${viewModel.ognFlights.value.size + ognFlights.size - viewModel.ognFlights.value.size} flights shown")
                        }
                    }
                )
            }

            // Show search status, error, or results
            when {
                // Currently searching
                isSearching -> {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.padding(end = 16.dp)
                            )
                            Text(
                                text = "🔍 Loading flights...",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                // Found results
                ognFlights.isNotEmpty() -> {
                    val totalFlights = viewModel.ognFlights.value.size + ognFlights.size - viewModel.ognFlights.value.size
                    Text(
                        text = if (registrationFilter.isNotEmpty()) {
                            "✈️ ${ognFlights.size} of $totalFlights flight(s) matching '$registrationFilter' on ${searchDate.toFormattedString()}:"
                        } else {
                            "✈️ ${ognFlights.size} flight(s) on ${searchDate.toFormattedString()}:"
                        },
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    ognFlights.forEach { flight ->
                        OutlinedCard(
                            onClick = {
                                // Fill form with selected flight data
                                flightRegistration = flight.registration
                                gliderType = flight.aircraftModel
                                takeoff = flight.takeoffAirfield
                                landing = flight.landingAirfield
                                launchType = flight.launchMethod
                                duration = (flight.duration / 60).toString() // Convert seconds to minutes
                                viewModel.clearSearch()
                                lastSearchTerm = ""
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "${flight.registration} - ${flight.aircraftModel}",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    text = "${flight.takeoffTime} → ${flight.landingTime} (${formatDuration(flight.duration)})",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "${flight.takeoffAirfield} → ${flight.landingAirfield}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                if (flight.maxAltitude > 0) {
                                    Text(
                                        text = "Max altitude: ${flight.maxAltitude}m",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                }
                                Text(
                                    text = "Launch: ${flight.launchMethod}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }

                // Search completed but no results (and we have searched for something)
                !isSearching && ognFlights.isEmpty() &&
                        searchAirfield.length >= 2 && lastSearchTerm.isNotEmpty() -> {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = if (registrationFilter.isNotEmpty()) "❌ No flights found matching filter" else "❌ No flights found",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                            Text(
                                text = if (registrationFilter.isNotEmpty()) {
                                    "No flights found matching '$registrationFilter' at '$searchAirfield' on ${searchDate.toFormattedString()}"
                                } else {
                                    "No flights found at '$searchAirfield' on ${searchDate.toFormattedString()}"
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "Try a different ICAO code or date. Popular gliding sites: EGHL (Lasham), EGAD (Dunstable), EGYK (Sutton Bank), EGTB (Booker), EGBF (Bidford)",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Search date picker dialog
            if (showSearchDatePicker) {
                val searchDatePickerState = rememberDatePickerState(
                    initialSelectedDateMillis = searchDate.time
                )
                DatePickerDialog(
                    onDismissRequest = { showSearchDatePicker = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                searchDate = searchDatePickerState.selectedDateMillis?.let { Date(it) } ?: searchDate
                                showSearchDatePicker = false
                                // Re-trigger search with new date if airfield is not empty
                                if (searchAirfield.trim().length >= 2) {
                                    val currentSearchTerm = "${searchAirfield.trim().uppercase()}-${searchDate.time}"
                                    lastSearchTerm = currentSearchTerm
                                    viewModel.searchFlightsByAirfield(searchAirfield.trim().uppercase(), searchDate)
                                }
                            }
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showSearchDatePicker = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = searchDatePickerState)
                }
            }

            OutlinedTextField(
                value = flightRegistration,
                onValueChange = { flightRegistration = it.uppercase() },
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

            Text(
                text = "Manual Entry",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 16.dp)
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

            Button(
                onClick = { showDatePicker = true },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Select Date: ${selectedDate.toFormattedString()}")
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
                    val flight = Flight(
                        registration = flightRegistration,
                        p2 = p2,
                        notes = notes,
                        gliderType = gliderType,
                        takeoff = takeoff,
                        landing = landing,
                        launchType = launchType,
                        duration = duration.toLongOrNull() ?: 0,
                        date = selectedDate
                    )
                    viewModel.insertFlight(flight)
                    // Clear form
                    flightRegistration = ""
                    searchAirfield = ""
                    p2 = ""
                    notes = ""
                    gliderType = ""
                    takeoff = ""
                    landing = ""
                    launchType = ""
                    duration = ""
                    lastSearchTerm = ""
                    viewModel.clearSearch()
                    // Navigate back to flight list
                    onNavigateToFlightList()
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Save Flight")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightListScreen(
    viewModel: FlightLogViewModel,
    onNavigateToForm: () -> Unit
) {
    val flights by viewModel.allFlights.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Flight Log") },
                actions = {
                    IconButton(onClick = onNavigateToForm) {
                        Icon(Icons.Default.Add, contentDescription = "Add Flight")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(flights) { flight ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${flight.registration} - ${flight.gliderType}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "${flight.takeoff} → ${flight.landing}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "${flight.date.toFormattedString()} - ${flight.duration} min",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            if (flight.p2?.isNotEmpty() == true) {
                                Text(
                                    text = "P2: ${flight.p2}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            if (flight.notes?.isNotEmpty() == true) {
                                Text(
                                    text = "Notes: ${flight.notes}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        IconButton(
                            onClick = { viewModel.deleteFlight(flight) }
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete Flight",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
    }
}

fun Date.toFormattedString(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(this)
}

fun formatDuration(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    return if (hours > 0) {
        "${hours}h ${minutes}m"
    } else {
        "${minutes}m"
    }
}