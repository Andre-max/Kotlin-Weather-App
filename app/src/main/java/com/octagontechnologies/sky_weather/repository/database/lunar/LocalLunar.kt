package com.octagontechnologies.sky_weather.repository.database.lunar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octagontechnologies.sky_weather.domain.Lunar

@Entity(tableName = "localLunar")
data class LocalLunar(
        @PrimaryKey(autoGenerate = false)
        val lunarKey: Int = 20,

        @ColumnInfo
        val lunarForecast: Lunar
)