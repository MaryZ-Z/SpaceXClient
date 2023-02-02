package com.example.spacexclient.data.launchdetail

import com.squareup.moshi.Json

data class QueryCrew(
    @Json(name = "launch_id")
    val launchId: String
)