package com.example.spacexclient.data.launches

import com.squareup.moshi.Json

data class DateUtc(
    @Json(name = "date_utc")
    val dateUtc: FromToDate
)