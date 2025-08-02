package com.soarlog.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.soarlog.app.data.AppDatabase
import com.soarlog.app.models.Flight
import com.soarlog.app.network.ApiClient
import com.soarlog.app.repository.FlightRepository
import com.soarlog.app.ui.screens.EditFlightScreen
import com.soarlog.app.ui.screens.FlightDetailsScreen
import com.soarlog.app.ui.screens.FlightListScreen
import com.soarlog.app.ui.screens.StatisticsScreen
import com.soarlog.app.ui.theme.SoarLogTheme
import com.soarlog.app.viewmodel.FlightLogViewModel
import com.soarlog.app.viewmodel.FlightLogViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getDatabase(this)
        val repository = FlightRepository(ApiClient.ognApiService, database.flightDao())
        val viewModelFactory = FlightLogViewModelFactory(repository)
        setContent {
            SoarLogTheme {
                val viewModel: FlightLogViewModel = viewModel(factory = viewModelFactory)
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.List, contentDescription = "Flights") },
                                selected = false,
                                onClick = { navController.navigate("flight-list") }
                            )
                            NavigationBarItem(
                                icon = {
                                    FloatingActionButton(onClick = { navController.navigate("logbook") }) {
                                        Icon(Icons.Default.Add, contentDescription = "Log Flight")
                                    }
                                },
                                selected = false,
                                onClick = { navController.navigate("logbook") }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.BarChart, contentDescription = "Statistics") },
                                selected = false,
                                onClick = { navController.navigate("statistics") }
                            )
                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "flight-list",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("logbook") {
                            FlightLogForm(viewModel, onNavigateToFlightList = {
                                navController.navigate("flight-list")
                            })
                        }
                        composable("flight-list") {
                            val flights by viewModel.allFlights.collectAsState(initial = emptyList())
                            FlightListScreen(
                                flights = flights,
                                viewModel = viewModel,
                                onFlightClick = { flightId ->
                                    navController.navigate("flight-details/$flightId")
                                }
                            )
                        }
                        composable("statistics") {
                            val flights by viewModel.allFlights.collectAsState(initial = emptyList())
                            StatisticsScreen(flights = flights)
                        }
                        composable(
                            "flight-details/{flightId}",
                            arguments = listOf(navArgument("flightId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val flightId = backStackEntry.arguments?.getInt("flightId")
                            flightId?.let {
                                val flight by viewModel.getFlightById(it).collectAsState()
                                flight?.let { f ->
                                    FlightDetailsScreen(
                                        flight = f,
                                        onEdit = {
                                            navController.navigate("edit-flight/$flightId")
                                        },
                                        onBack = {
                                            navController.popBackStack()
                                        }
                                    )
                                }
                            }
                        }
                        composable(
                            "edit-flight/{flightId}",
                            arguments = listOf(navArgument("flightId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val flightId = backStackEntry.arguments?.getInt("flightId")
                            flightId?.let {
                                val flight by viewModel.getFlightById(it).collectAsState()
                                flight?.let { f ->
                                    EditFlightScreen(
                                        flight = f,
                                        viewModel = viewModel,
                                        onBack = {
                                            navController.popBackStack()
                                        }
                                    )
                                }
                            }
                        }
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

    // Hidden fields for takeoff/landing times
    var takeoffTime by remember { mutableStateOf("") }
    var landingTime by remember { mutableStateOf("") }

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
                text = "Browse Flights by Location",
                style = MaterialTheme.typography.headlineMedium
            )

            val ognFlights by viewModel.ognFlights.collectAsState()
            val isSearching by viewModel.isSearching.collectAsState()

            // Airfield search field
            OutlinedTextField(
                value = searchAirfield,
                onValueChange = { newValue ->
                    searchAirfield = newValue.uppercase()
                    val cleanAirfield = newValue.trim().uppercase()
                    val currentSearchTerm = "$cleanAirfield-${searchDate.time}"

                    if (cleanAirfield.length >= 2 && currentSearchTerm != lastSearchTerm) {
                        println("✅ Triggering airfield search: '$cleanAirfield'")
                        lastSearchTerm = currentSearchTerm
                        viewModel.searchFlightsByAirfield(cleanAirfield, searchDate)
                    } else if (cleanAirfield.length < 2) {
                        lastSearchTerm = ""
                        viewModel.clearSearch()
                    }
                },
                label = { Text("Airfield (e.g. LASHAM, DUNSTABLE, GB-0001)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                supportingText = {
                    Text("Enter airfield name or code to browse all flights")
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
                    Text(
                        text = "✈️ ${ognFlights.size} flight(s) on ${searchDate.toFormattedString()}:",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    ognFlights.forEach { flight ->
                        OutlinedCard(
                            onClick = {
                                // Fill form with selected flight data including times
                                flightRegistration = flight.registration
                                gliderType = flight.aircraftModel
                                takeoff = flight.takeoffAirfield
                                landing = flight.landingAirfield
                                launchType = flight.launchMethod
                                duration = (flight.duration / 60).toString() // Convert seconds to minutes
                                selectedDate = searchDate // Use the search date

                                // Store the takeoff/landing times (hidden from UI)
                                takeoffTime = flight.takeoffTime
                                landingTime = flight.landingTime

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
                                text = "❌ No flights found",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                            Text(
                                text = "No flights found at '$searchAirfield' on ${searchDate.toFormattedString()}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "Try a different airfield or date. Common airfield codes: LASHAM, DUNSTABLE, GB-0001, EGKA",
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
                        date = selectedDate,
                        takeoffTime = takeoffTime.ifBlank { null },
                        landingTime = landingTime.ifBlank { null }
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
                    takeoffTime = ""
                    landingTime = ""
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

fun Date.toFormattedString(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this)
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

@Preview(showBackground = true)
@Composable
fun FlightLogFormPreview() {
    SoarLogTheme {
        //FlightLogForm()
    }
}