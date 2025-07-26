package com.soarlog.app.models

import com.squareup.moshi.Json

data class OgnFlight(
    @Json(name = "device_id") val deviceId: String,
    @Json(name = "takeoff_ts") val takeoffTs: Long,
    @Json(name = "landing_ts") val landingTs: Long,
    @Json(name = "takeoff_lat") val takeoffLat: Double,
    @Json(name = "takeoff_lon") val takeoffLon: Double,
    @Json(name = "landing_lat") val landingLat: Double,
    @Json(name = "landing_lon") val landingLon: Double,
    val registration: String,
    val cn: String,
    @Json(name = "aircraft_model") val aircraftModel: String,
    @Json(name = "launch_type") val launchType: String
)
