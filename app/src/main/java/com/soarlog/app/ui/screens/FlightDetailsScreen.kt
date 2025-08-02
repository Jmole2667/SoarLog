package com.soarlog.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.soarlog.app.models.Flight
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightDetailsScreen(
    flight: Flight,
    onEdit: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Flight Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit Flight")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            DetailRow("Registration:", flight.registration)
            flight.p2?.let { if (it.isNotBlank()) DetailRow("P2:", it) }
            flight.gliderType?.let { if (it.isNotBlank()) DetailRow("Glider Type:", it) }
            flight.takeoff?.let { if (it.isNotBlank()) DetailRow("Takeoff:", it) }
            flight.landing?.let { if (it.isNotBlank()) DetailRow("Landing:", it) }
            flight.launchType?.let { if (it.isNotBlank()) DetailRow("Launch Type:", it) }
            if (flight.duration > 0) DetailRow("Duration:", "${flight.duration} minutes")
            DetailRow("Date:", SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(flight.date))
            flight.takeoffTime?.let { if (it.isNotBlank()) DetailRow("Takeoff Time:", it) }
            flight.landingTime?.let { if (it.isNotBlank()) DetailRow("Landing Time:", it) }
            flight.notes?.let { if (it.isNotBlank()) DetailRow("Notes:", it) }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            modifier = Modifier.weight(2f)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}
