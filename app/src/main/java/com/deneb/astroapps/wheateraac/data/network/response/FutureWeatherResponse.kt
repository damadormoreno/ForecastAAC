package com.deneb.astroapps.wheateraac.data.network.response

import com.deneb.astroapps.wheateraac.data.db.entity.WeatherLocation
import com.google.gson.annotations.SerializedName

data class FutureWeatherResponse(
    @SerializedName("forecast")
    val futureWeatherEntries: ForecastDaysContainer,
    val location: WeatherLocation
)