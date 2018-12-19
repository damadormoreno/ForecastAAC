package com.deneb.astroapps.wheateraac.data.provider

import com.deneb.astroapps.wheateraac.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem() : UnitSystem
}