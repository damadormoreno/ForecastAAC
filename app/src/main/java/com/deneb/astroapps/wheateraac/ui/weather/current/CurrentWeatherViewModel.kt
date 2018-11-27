package com.deneb.astroapps.wheateraac.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.deneb.astroapps.wheateraac.data.Repository.ForecastRepository
import com.deneb.astroapps.wheateraac.internal.UnitSystem
import com.deneb.astroapps.wheateraac.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC//get from settings later

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
