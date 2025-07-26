package com.soarlog.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.activity.viewModels
import androidx.compose.material3.Button
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soarlog.app.data.AppDatabase
import com.soarlog.app.network.RetrofitInstance
import com.soarlog.app.repository.FlightRepository
import com.soarlog.app.ui.theme.SoarLogTheme
import com.soarlog.app.viewmodel.FlightLogViewModel
import com.soarlog.app.models.Flight
import java.util.Date

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.soarlog.app.ui.screens.FlightListScreen
import com.soarlog.app.ui.screens.StatisticsScreen
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.soarlog.app.ui.screens.FlightListScreen
import com.soarlog.app.ui.screens.StatisticsScreen
import com.soarlog.app.viewmodel.FlightLogViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getDatabase(this)
        val repository = FlightRepository(RetrofitInstance.api, database.flightDao())
        val viewModel by viewModels<FlightLogViewModel> {
            FlightLogViewModelFactory(repository)
        }
        setContent {
            SoarLogTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            BottomNavigationItem(
                                icon = { Icon(Icons.Default.List, contentDescription = "Flights") },
                                selected = false,
                                onClick = { navController.navigate("flight-list") }
                            )
                            BottomNavigationItem(
                                icon = { Icon(Icons.Default.Create, contentDescription = "Log Flight") },
                                selected = false,
                                onClick = { navController.navigate("logbook") }
                            )
                            BottomNavigationItem(
                                icon = { Icon(Icons.Default.Star, contentDescription = "Statistics") },
                                selected = false,
                                onClick = { navController.navigate("statistics") }
                            )
                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "logbook",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("logbook") {
                            FlightLogForm(viewModel, onNavigateToFlightList = {
                                navController.navigate("flight-list")
                            })
                        }
                        composable("flight-list") {
                            val flights by viewModel.allFlights.collectAsState()
                            FlightListScreen(flights = flights)
                        }
                        composable("statistics") {
                            val flights by viewModel.allFlights.collectAsState()
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
    val flightRegistration by remember { mutableStateOf("") }
    val p2 by remember { mutableStateOf("") }
    val notes by remember { mutableStateOf("") }
    var gliderType by remember { mutableStateOf("") }
    var takeoff by remember { mutableStateOf("") }
    var landing by remember { mutableStateOf("") }
    var launchType by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    val flights by viewModel.flights.collectAsState()

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
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val flight = Flight(
                    registration = flightRegistration,
                    p2 = p2,
                    notes = notes,
                    gliderType = gliderType,
                    takeoff = takeoff,
                    landing = landing,
                    launchType = launchType,
                    duration = duration.toLongOrNull() ?: 0,
                    date = Date()
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
                Icon(Icons.Default.Add, contentDescription = "Log Flight")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Auto",
                style = MaterialTheme.typography.headlineMedium
            )
            OutlinedTextField(
                value = flightRegistration,
                onValueChange = { flightRegistration = it },
                label = { Text("Flight Registration") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = { viewModel.getFlights(flightRegistration) }) {
                Text("Search")
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

            flights.firstOrNull()?.let { flight ->
                gliderType = flight.aircraftModel
                takeoff = "${flight.takeoffLat}, ${flight.takeoffLon}"
                landing = "${flight.landingLat}, ${flight.landingLon}"
                launchType = flight.launchType
                duration = ((flight.landingTs - flight.takeoffTs) / 60).toString()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlightLogFormPreview() {
    SoarLogTheme {
        FlightLogForm()
    }
}
