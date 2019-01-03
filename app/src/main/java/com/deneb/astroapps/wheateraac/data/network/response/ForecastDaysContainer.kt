package com.deneb.astroapps.wheateraac.data.network.response

import com.deneb.astroapps.wheateraac.data.db.entity.FutureWeatherEntry
import com.google.gson.annotations.SerializedName

data class ForecastDaysContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)