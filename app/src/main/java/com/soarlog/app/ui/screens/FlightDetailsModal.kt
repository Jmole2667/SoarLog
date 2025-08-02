package com.soarlog.app.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.FlightLand
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.soarlog.app.models.Flight
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightDetailsModal(
    flight: Flight,
    onEdit: () -> Unit,
    onDismiss: () -> Unit
) {
    // Animation for the modal appearance
    var showContent by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (showContent) 1f else 0.8f,
        label = "scale"
    )

    LaunchedEffect(Unit) {
        showContent = true
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { onDismiss() },
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.85f)
                    .scale(scale)
                    .clickable(enabled = false) { /* Prevent dismissing when clicking on card */ },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Header with edit button
                    TopAppBar(
                        title = { 
                            Text(
                                text = "Flight Details",
                                style = MaterialTheme.typography.headlineSmall
                            )
                        },
                        actions = {
                            IconButton(onClick = onEdit) {
                                Icon(
                                    Icons.Default.Edit,
                                    contentDescription = "Edit Flight",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                            IconButton(onClick = onDismiss) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    )

                    // Flight details content
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Registration (Main title)
                        Text(
                            text = flight.registration,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        // Date Section
                        SectionCard(title = "Date") {
                            Text(
                                text = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault()).format(flight.date),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                        // Times and Location Section
                        if (hasTimesOrLocations(flight)) {
                            SectionCard(title = "Flight Details") {
                                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                    // Times row
                                    if (!flight.takeoffTime.isNullOrBlank() || !flight.landingTime.isNullOrBlank()) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                                        ) {
                                            if (!flight.takeoffTime.isNullOrBlank()) {
                                                TimeInfoCard(
                                                    label = "Takeoff Time",
                                                    time = formatTime(flight.takeoffTime),
                                                    icon = Icons.Default.FlightTakeoff,
                                                    modifier = Modifier.weight(1f)
                                                )
                                            }
                                            if (!flight.landingTime.isNullOrBlank()) {
                                                TimeInfoCard(
                                                    label = "Landing Time",
                                                    time = formatTime(flight.landingTime),
                                                    icon = Icons.Default.FlightLand,
                                                    modifier = Modifier.weight(1f)
                                                )
                                            }
                                        }
                                    }

                                    // Duration
                                    if (flight.duration > 0) {
                                        Card(
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = CardDefaults.cardColors(
                                                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                                            )
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(12.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                                            ) {
                                                Icon(
                                                    Icons.Default.Flight,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.primary,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                                Column {
                                                    Text(
                                                        text = "Flight Duration",
                                                        style = MaterialTheme.typography.labelMedium,
                                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                    Text(
                                                        text = formatDuration(flight.duration.toLong()),
                                                        style = MaterialTheme.typography.bodyLarge,
                                                        fontWeight = FontWeight.Bold,
                                                        color = MaterialTheme.colorScheme.primary
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    // Locations row with arrow
                                    if (flight.takeoff.isNotBlank() || (flight.landing.isNotBlank() && flight.landing != flight.takeoff)) {
                                        Card(
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = CardDefaults.cardColors(
                                                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
                                            )
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(12.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                                            ) {
                                                if (flight.takeoff.isNotBlank()) {
                                                    Column(modifier = Modifier.weight(1f)) {
                                                        Text(
                                                            text = "Takeoff Location",
                                                            style = MaterialTheme.typography.labelMedium,
                                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                                        )
                                                        Text(
                                                            text = flight.takeoff,
                                                            style = MaterialTheme.typography.bodyMedium,
                                                            fontWeight = FontWeight.Medium
                                                        )
                                                    }
                                                }

                                                if (flight.landing.isNotBlank() && flight.landing != flight.takeoff) {
                                                    Icon(
                                                        Icons.Default.ArrowForward,
                                                        contentDescription = "to",
                                                        tint = MaterialTheme.colorScheme.primary,
                                                        modifier = Modifier.size(20.dp)
                                                    )
                                                    Column(modifier = Modifier.weight(1f)) {
                                                        Text(
                                                            text = "Landing Location",
                                                            style = MaterialTheme.typography.labelMedium,
                                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                                        )
                                                        Text(
                                                            text = flight.landing,
                                                            style = MaterialTheme.typography.bodyMedium,
                                                            fontWeight = FontWeight.Medium
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // Aircraft Section
                        if (flight.gliderType.isNotBlank() || flight.launchType.isNotBlank()) {
                            SectionCard(title = "Aircraft & Launch") {
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    if (flight.gliderType.isNotBlank()) {
                                        DetailRow("Aircraft Type", flight.gliderType)
                                    }
                                    if (flight.launchType.isNotBlank()) {
                                        DetailRow("Launch Method", flight.launchType)
                                    }
                                }
                            }
                        }

                        // People Section
                        if (!flight.p2.isNullOrBlank()) {
                            SectionCard(title = "Crew") {
                                DetailRow("P2 Pilot", flight.p2)
                            }
                        }

                        // Notes Section
                        if (!flight.notes.isNullOrBlank()) {
                            SectionCard(title = "Notes") {
                                Text(
                                    text = flight.notes,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TimeInfoCard(
    label: String,
    time: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(18.dp)
            )
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = time,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

private fun hasTimesOrLocations(flight: Flight): Boolean {
    return !flight.takeoffTime.isNullOrBlank() || 
           !flight.landingTime.isNullOrBlank() || 
           flight.takeoff.isNotBlank() || 
           (flight.landing.isNotBlank() && flight.landing != flight.takeoff) ||
           flight.duration > 0
}

private fun formatTime(timeString: String?): String {
    if (timeString.isNullOrBlank()) return ""
    
    // Convert formats like "10h25" to "10:25"
    return timeString.replace("h", ":")
}

private fun formatDuration(minutes: Long): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    
    return when {
        hours > 0 && remainingMinutes > 0 -> "${hours}h ${remainingMinutes}m"
        hours > 0 -> "${hours}h"
        else -> "${minutes}m"
    }
}