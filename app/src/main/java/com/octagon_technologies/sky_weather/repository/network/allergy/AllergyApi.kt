package com.octagon_technologies.sky_weather.repository.network.allergy

import com.octagon_technologies.sky_weather.repository.network.allergy.models.AllergyResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


const val AllergyBaseUrl = "https://api.ambeedata.com/"
const val AllergyApiKey = "oWJChLwX5MM5MqUopRh1Qr3QLpEkQw6gExN5QQa0"

interface AllergyApi {
    @Headers(
        "Content-Type: application/json",
        "x-api-key: $AllergyApiKey"
    )
    @GET("latest/pollen/by-lat-lng")
    suspend fun getAllergyResponse(
        @Query("lat") lat: Double,
        @Query("lng") lon: Double
    ): AllergyResponse
}