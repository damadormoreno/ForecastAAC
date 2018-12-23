package com.deneb.astroapps.wheateraac.data.provider

import com.deneb.astroapps.wheateraac.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}