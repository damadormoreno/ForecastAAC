package com.deneb.astroapps.wheateraac.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.storage.StorageVolume
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.lifecycle.Observer

import com.deneb.astroapps.wheateraac.R
import com.deneb.astroapps.wheateraac.data.ApixuWeatherApiService
import com.deneb.astroapps.wheateraac.data.network.ConnectivityInterceptorImpl
import com.deneb.astroapps.wheateraac.data.network.WeatherNetworkDataSource
import com.deneb.astroapps.wheateraac.data.network.WeatherNetworkDataSourceImpl
import com.deneb.astroapps.wheateraac.internal.glide.GlideApp
import com.deneb.astroapps.wheateraac.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment() , KodeinAware{
    override val kodein by closestKodein()

    private val viewModelFactory : CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if (it == null) return@Observer

            group_loading.visibility = View.GONE
            updateLocation("Madrid")
            updateDateToToday()
            updateTemperatures(it.temperature, it.feelsLikeTemperature)
            updateCondition(it.conditionText)
            updatePrecipitation(it.precipitationVolume)
            updateWind(it.windDirection, it.windSpeed)
            updateVisibility(it.visibilityDistance)
            GlideApp.with(this@CurrentWeatherFragment)
                .load("http:${it.conditionIconUrl}")
                .into(imageView_condition_icon)
        })
    }

    private fun chooseLocalizeUnitAbbreviation(metric: String, imperial: String): String {
        return if (viewModel.isMetric) metric else imperial
    }

    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToToday() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double) {
        val unitAbbreviation = chooseLocalizeUnitAbbreviation("ºC", "ºF")
        textView_temperature.text = "$temperature$unitAbbreviation"
        textView_feels_like_temperature.text = "Feels like $feelsLike$unitAbbreviation"
    }

    private fun updateCondition(condition: String) {
        textView_condition.text = condition
    }

    private fun updatePrecipitation(precipitationVolume: Double) {
        val unitAbbreviation = chooseLocalizeUnitAbbreviation("mm", "in")
        textView_precipitation.text = "Precipitation $precipitationVolume $unitAbbreviation"
    }
    private fun updateWind(windDirection: String, windSpeed: Double) {
        val unitAbbreviation = chooseLocalizeUnitAbbreviation("kph", "mph")
        textView_wind.text = "Wind $windDirection, $windSpeed $unitAbbreviation"
    }
    private fun updateVisibility(visibilityDistance: Double) {
        val unitAbbreviation = chooseLocalizeUnitAbbreviation("km", "mi")
        textView_visibility.text = "Visibility $visibilityDistance $unitAbbreviation"
    }
}
