package com.soarlog.app.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OgnFlightResponse(
    val airfield: OgnAirfield?,
    val devices: List<OgnDevice>?,
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
    val address: String?,
    val aircraft: String?,
    val registration: String?
)

@JsonClass(generateAdapter = true)
data class OgnFlight(
    val device: Int,
    val start: String?, // Time format like "09h34"
    val stop: String?, // Time format like "09h40"
    val duration: Int?, // Duration in seconds
    @Json(name = "max_alt") val maxAlt: Int?,
    @Json(name = "max_height") val maxHeight: Int?,
    @Json(name = "start_q") val startQ: Int?,
    @Json(name = "stop_q") val stopQ: Int?,
    @Json(name = "start_tsp") val startTsp: Long?,
    @Json(name = "stop_tsp") val stopTsp: Long?,
    val tow: Int?, // Reference to towing aircraft device index
    val towing: Boolean?,
    val warn: Boolean?
)