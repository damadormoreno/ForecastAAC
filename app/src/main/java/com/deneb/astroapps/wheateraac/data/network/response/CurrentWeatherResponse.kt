package com.deneb.astroapps.wheateraac.data.network.response

import com.deneb.astroapps.wheateraac.data.db.entity.CurrentWeatherEntry
import com.deneb.astroapps.wheateraac.data.db.entity.WeatherLocation
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: WeatherLocation
)