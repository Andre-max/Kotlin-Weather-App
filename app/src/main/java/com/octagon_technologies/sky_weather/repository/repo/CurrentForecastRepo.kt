package com.octagon_technologies.sky_weather.repository.repo

import androidx.lifecycle.map
import com.octagon_technologies.sky_weather.domain.Location
import com.octagon_technologies.sky_weather.repository.database.toLocalCurrentForecast
import com.octagon_technologies.sky_weather.repository.database.weather.current.CurrentForecastDao
import com.octagon_technologies.sky_weather.repository.network.weather.TomorrowApi
import com.octagon_technologies.sky_weather.utils.Units
import javax.inject.Inject

class CurrentForecastRepo @Inject constructor(
    private val weatherApi: TomorrowApi,
    private val currentForecastDao: CurrentForecastDao
) {

    val currentForecast = currentForecastDao.getLocalCurrentForecast().map { it.currentForecast }

    suspend fun refreshCurrentForecast(
        location: Location,
        units: Units?
    ) {
        val currentForecastResponse = weatherApi.getCurrentForecast(
            location = location.getCoordinates(),
            units = units?.value ?: Units.METRIC.value
        )
        currentForecastDao.insertLocalCurrentForecast(currentForecastResponse.toLocalCurrentForecast())
    }

}