package com.example.spacexclient.data.launches

import com.squareup.moshi.Json

data class QueryLaunches(
    val links: Links,
    @Json(name = "date_utc")
    val dateUtc: String,
    val success: Boolean?,
    val details: String?,
    val name: String,
    val cores: List<Cores>,
    val id: String,
    @Json(name = "flight_number")
    val flightNumber: String
)