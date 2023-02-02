package com.example.spacexclient.data.launchdetail

import com.squareup.moshi.Json

data class QueryDetail(
    @Json(name = "flight_number")
    val flightNumber: String
)
