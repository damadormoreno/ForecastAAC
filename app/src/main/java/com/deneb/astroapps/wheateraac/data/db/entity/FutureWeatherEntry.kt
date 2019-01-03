package com.deneb.astroapps.wheateraac.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "future_weather", indices = [Index(value = ["date"], unique = true)])
data class FutureWeatherEntry(
    //val astro: Astro,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val date: String,
  /*  @SerializedName("date_epoch")
    val dateEpoch: Int,*/
    @Embedded
    val day: Day
)