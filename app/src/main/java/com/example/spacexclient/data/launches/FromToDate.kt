package com.example.spacexclient.data.launches

import android.os.Build
import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.*

data class FromToDate(
    @Json(name = "\$gte")
    val gte: String = fromDate(),
    @Json(name = "\$lte")
    val lte: String = toDate()
) {
    companion object {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)

        private fun fromDate(): String = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val date = Date(2021, 1, 1)
            dateFormat.format(date.time)
        } else {
            LocalDate.of(2021, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC).toString()
        }

        private fun toDate() = dateFormat.format(Date())
    }
}