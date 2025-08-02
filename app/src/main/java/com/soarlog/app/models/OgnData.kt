package com.soarlog.app.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OgnFlightResponse(
    val airfield: OgnAirfield?,
    val devices: List<OgnDevice>?, // Changed from Map to List
    val flights: List<OgnFlight>?
)

@JsonClass(generateAdapter = true)
data class OgnAirfield(
    val code: String?,
    val country: String?,
    val elevation: Int?,
    val name: String?
)

@JsonClass(generateAdapter = true)
data class OgnDevice(
    val address: String?, // The device identifier
    val aircraft: String?, // Aircraft type
    val registration: String? // Registration like G-CKOW
)

@JsonClass(generateAdapter = true)
data class OgnFlight(
    val device: Int, // This is now an INDEX into the devices array
    @Json(name = "takeoff_time") val takeoffTime: String,
    @Json(name = "landing_time") val landingTime: String,
    val duration: Int,
    @Json(name = "max_altitude") val maxAltitude: Int?,
    @Json(name = "takeoff_airfield") val takeoffAirfield: OgnFlightAirfield?,
    @Json(name = "landing_airfield") val landingAirfield: OgnFlightAirfield?,
    @Json(name = "launch_method") val launchMethod: String?
)

@JsonClass(generateAdapter = true)
data class OgnFlightAirfield(
    @Json(name = "short_name") val shortName: String?,
    val name: String?
)