package com.soarlog.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.soarlog.app.data.AppDatabase
import com.soarlog.app.models.Flight
import com.soarlog.app.network.RetrofitInstance
import com.soarlog.app.repository.FlightRepository
import com.soarlog.app.ui.screens.FlightListScreen
import com.soarlog.app.ui.screens.StatisticsScreen
import com.soarlog.app.ui.theme.SoarLogTheme
import com.soarlog.app.viewmodel.FlightLogViewModel
import com.soarlog.app.viewmodel.FlightLogViewModelFactory
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getDatabase(this)
        val repository = FlightRepository(RetrofitInstance.api, database.flightDao())
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
                            FlightListScreen(flights = flights, viewModel = viewModel)
                        }
                        composable("statistics") {
                            val flights by viewModel.allFlights.collectAsState(initial = emptyList())
                            StatisticsScreen(flights = flights)
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
    var p2 by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var gliderType by remember { mutableStateOf("") }
    var takeoff by remember { mutableStateOf("") }
    var landing by remember { mutableStateOf("") }
    var launchType by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<Date>(Date()) }
    var showDatePicker by remember { mutableStateOf(false) }

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
        },
        floatingActionButton = {}
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Auto",
                    style = MaterialTheme.typography.headlineMedium
                )
                var expanded by remember { mutableStateOf(false) }
                val ognFlights by viewModel.ognFlights.collectAsState(initial = emptyList())

                Box {
                    OutlinedTextField(
                        value = flightRegistration,
                        onValueChange = {
                            flightRegistration = it.uppercase()
                            expanded = it.isNotEmpty()
                            if (it.isNotEmpty()) {
                                viewModel.searchFlights(it)
                            }
                        },
                        label = { Text("Flight Registration") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ognFlights.forEach { flight ->
                            DropdownMenuItem(
                                text = { Text(flight.registration) },
                                onClick = {
                                    flightRegistration = flight.registration
                                    gliderType = flight.gliderType
                                    takeoff = flight.takeoff
                                    landing = flight.landing
                                    expanded = false
                                }
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = p2,
                    onValueChange = { p2 = it },
                    label = { Text("P2 (optional)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Manual",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = gliderType,
                    onValueChange = { gliderType = it },
                    label = { Text("Glider Type") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = takeoff,
                    onValueChange = { takeoff = it },
                    label = { Text("Takeoff") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = landing,
                    onValueChange = { landing = it },
                    label = { Text("Landing") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = launchType,
                    onValueChange = { launchType = it },
                    label = { Text("Launch Type") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = duration,
                    onValueChange = { duration = it },
                    label = { Text("Duration (minutes)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(onClick = { showDatePicker = true }) {
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

                Button(onClick = {
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
                    flightRegistration = ""
                    p2 = ""
                    notes = ""
                    gliderType = ""
                    takeoff = ""
                    landing = ""
                    launchType = ""
                    duration = ""
                }) {
                    Text("Save")
                }

            }
        }
    }
}

fun Date.toFormattedString(): String {
    val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    return sdf.format(this)
}

@Preview(showBackground = true)
@Composable
fun FlightLogFormPreview() {
    SoarLogTheme {
        //FlightLogForm()
    }
}
