package com.deneb.astroapps.wheateraac.ui.base

import androidx.lifecycle.ViewModel
import com.deneb.astroapps.wheateraac.data.Repository.ForecastRepository
import com.deneb.astroapps.wheateraac.data.provider.UnitProvider
import com.deneb.astroapps.wheateraac.internal.UnitSystem
import com.deneb.astroapps.wheateraac.internal.lazyDeferred

abstract class WeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}