package com.octagon_technologies.sky_weather.ui.daily_forecast.daily_tab

import androidx.lifecycle.ViewModel
import com.octagon_technologies.sky_weather.ads.AdRepo
import com.octagon_technologies.sky_weather.domain.daily.TimePeriod
import com.octagon_technologies.sky_weather.domain.daily.getFormattedCloudCover
import com.octagon_technologies.sky_weather.domain.daily.getFormattedFeelsLike
import com.octagon_technologies.sky_weather.domain.daily.getFormattedHumidity
import com.octagon_technologies.sky_weather.domain.daily.getFormattedSeaLevelPressure
import com.octagon_technologies.sky_weather.domain.daily.getFormattedSurfacePressure
import com.octagon_technologies.sky_weather.domain.daily.getFormattedTemp
import com.octagon_technologies.sky_weather.domain.daily.getFormattedUVIndex
import com.octagon_technologies.sky_weather.domain.getFormattedSeaLevelPressure
import com.octagon_technologies.sky_weather.domain.getFormattedSurfacePressure
import com.octagon_technologies.sky_weather.models.EachWeatherDescription
import com.octagon_technologies.sky_weather.repository.repo.SettingsRepo
import com.octagon_technologies.sky_weather.utils.Units
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DailyTabViewModel @Inject constructor(
    private val settingsRepo: SettingsRepo
) : ViewModel() {

    val adRepo = AdRepo()

    fun getWeatherDescriptionFromTimePeriod(timePeriod: TimePeriod): List<EachWeatherDescription> =
        listOf(
            EachWeatherDescription(
                "Temp",
                timePeriod.getFormattedTemp()
            ),
            EachWeatherDescription(
                "FeelsLike",
                timePeriod.getFormattedFeelsLike()
            ),
            EachWeatherDescription(
                "Humidity",
                timePeriod.getFormattedHumidity()
            ),
            EachWeatherDescription(
                "Average Wind",
                timePeriod.wind.getWindSpeedWithDirection(
                    settingsRepo.units.value,
                    settingsRepo.windDirectionUnits.value
                )
            ),
            EachWeatherDescription(
                "Cloud Cover",
                timePeriod.getFormattedCloudCover()
            ),
            EachWeatherDescription(
                "UV Index",
                timePeriod.getFormattedUVIndex()
            ),
            EachWeatherDescription(
                "Surface Pressure",
                timePeriod.getFormattedSurfacePressure(settingsRepo.units.value == Units.IMPERIAL)
            ),
            EachWeatherDescription(
                "Sea Level Pressure",
                timePeriod.getFormattedSeaLevelPressure(settingsRepo.units.value == Units.IMPERIAL)
            )
        )
}