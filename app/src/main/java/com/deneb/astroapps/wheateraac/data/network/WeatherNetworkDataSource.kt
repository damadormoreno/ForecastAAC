package com.deneb.astroapps.wheateraac.data.network

import androidx.lifecycle.LiveData
import com.deneb.astroapps.wheateraac.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {

    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )
}