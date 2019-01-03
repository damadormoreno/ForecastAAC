package com.deneb.astroapps.wheateraac.data.Repository

import androidx.lifecycle.LiveData
import com.deneb.astroapps.wheateraac.data.db.entity.WeatherLocation
import com.deneb.astroapps.wheateraac.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {

    suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}