package com.deneb.astroapps.wheateraac.data.db.unitlocalized.future

import org.threeten.bp.LocalDate

interface UnitSpecificFutureWeatherEntry {
    val date: LocalDate
    val avgTemperature: Double
    val conditionText: String
    val conditionIconUUrl: String
}