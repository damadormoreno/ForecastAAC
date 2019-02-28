package com.deneb.astroapps.wheateraac.ui.weather.future.list

import androidx.lifecycle.ViewModel;
import com.deneb.astroapps.wheateraac.data.Repository.ForecastRepository
import com.deneb.astroapps.wheateraac.data.provider.UnitProvider
import com.deneb.astroapps.wheateraac.internal.lazyDeferred
import com.deneb.astroapps.wheateraac.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureListWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weatherEntries by lazyDeferred {
        forecastRepository.getFutureWeatherList(LocalDate.now(), super.isMetricUnit)
    }
}
