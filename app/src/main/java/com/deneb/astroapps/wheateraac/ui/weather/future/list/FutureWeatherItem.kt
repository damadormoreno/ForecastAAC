package com.deneb.astroapps.wheateraac.ui.weather.future.list

import com.bumptech.glide.Glide
import com.deneb.astroapps.wheateraac.R
import com.deneb.astroapps.wheateraac.data.db.unitlocalized.future.list.MetricSimpleFutureWeatherEntry
import com.deneb.astroapps.wheateraac.data.db.unitlocalized.future.list.UnitSpecificSimpleFutureWeatherEntry
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_future_weather.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class FutureWeatherItem(
    val weatherEntry: UnitSpecificSimpleFutureWeatherEntry
) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_condition.text = weatherEntry.conditionText
            updateDate()
            updateTemperature()
            updateConditionImage()
        }
    }

    override fun getLayout(): Int = R.layout.item_future_weather

    private fun ViewHolder.updateDate() {
        val dtFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        textView_date.text = weatherEntry.date.format(dtFormatter)
    }

    private fun ViewHolder.updateTemperature() {
        val unitAbbreviation = if (weatherEntry is MetricSimpleFutureWeatherEntry) "ºC"
        else "ºF"
        textView_temperature.text = "${weatherEntry.avgTemperature}$unitAbbreviation"
    }

    private fun ViewHolder.updateConditionImage() {
        Glide.with(this.containerView)
            .load("http:" + weatherEntry.conditionIconUrl)
            .into(imageView_condition_icon)
    }
}
