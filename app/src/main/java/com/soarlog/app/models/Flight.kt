package com.soarlog.app.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "flight")
data class Flight(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val registration: String,
    val p2: String?,
    val notes: String?,
    val gliderType: String,
    val takeoff: String,
    val landing: String,
    val launchType: String,
    val duration: Long,
    val date: Date,
    val takeoffTime: String? = null, // Store takeoff time (e.g., "09:30")
    val landingTime: String? = null  // Store landing time (e.g., "10:45")
)