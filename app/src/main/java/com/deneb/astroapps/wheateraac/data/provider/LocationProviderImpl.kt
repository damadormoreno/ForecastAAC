package com.deneb.astroapps.wheateraac.data.provider

import com.deneb.astroapps.wheateraac.data.db.entity.WeatherLocation

class LocationProviderImpl : LocationProvider {
    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        return true
    }

    override suspend fun getPreferredLocationString(): String {
        return "Madrid"
    }
}