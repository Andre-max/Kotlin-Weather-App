package com.octagon_technologies.sky_weather.repository.network.single_forecast

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Precipitation(
    val units: String?,
    val value: Double?
)