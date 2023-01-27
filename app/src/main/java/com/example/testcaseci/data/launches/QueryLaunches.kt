package com.example.testcaseci.data.launches

import com.example.testcaseci.data.launchdetail.Crew
import com.squareup.moshi.Json

data class QueryLaunches(
    val links: Links,
    @Json(name = "date_utc")
    val dateUtc: String,
    val success: Boolean?,
    val details: String?,
    val crew: List<Crew>?,
    val name: String,
    val cores: List<Cores>,
    val id: String,
    @Json(name = "flight_number")
    val flightNumber: String
)