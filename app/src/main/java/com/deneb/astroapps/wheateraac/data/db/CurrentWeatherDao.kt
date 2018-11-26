package com.deneb.astroapps.wheateraac.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deneb.astroapps.wheateraac.data.db.entity.CURRENT_WEATHER_ID
import com.deneb.astroapps.wheateraac.data.db.entity.CurrentWeatherEntry
import com.deneb.astroapps.wheateraac.data.db.unitlocalized.ImperialCurrentWeatherEntry
import com.deneb.astroapps.wheateraac.data.db.unitlocalized.MetricCurrentWeatherEntry
import com.deneb.astroapps.wheateraac.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query(value = "select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query(value = "select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}