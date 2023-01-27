package com.example.testcaseci.data.launches

import com.squareup.moshi.Json

data class DateUtc(
    @Json(name = "date_utc")
    val dateUtc: FromToDate
)