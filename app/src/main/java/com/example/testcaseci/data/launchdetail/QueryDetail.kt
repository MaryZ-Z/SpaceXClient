package com.example.testcaseci.data.launchdetail

import com.squareup.moshi.Json

data class QueryDetail(
    @Json(name = "flight_number")
    val flightNumber: String
)
