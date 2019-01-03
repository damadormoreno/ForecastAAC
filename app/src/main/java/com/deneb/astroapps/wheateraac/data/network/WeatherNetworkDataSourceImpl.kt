package com.deneb.astroapps.wheateraac.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deneb.astroapps.wheateraac.data.ApixuWeatherApiService
import com.deneb.astroapps.wheateraac.data.network.response.CurrentWeatherResponse
import com.deneb.astroapps.wheateraac.data.network.response.FutureWeatherResponse
import com.deneb.astroapps.wheateraac.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {

        try {
            val fetchCurrentWeather = apixuWeatherApiService
                .getCurrentWeather(location,languageCode)
                .await()

            _downloadedCurrentWeather.postValue(fetchCurrentWeather)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    private val _downloadedFutureWeather = MutableLiveData<FutureWeatherResponse>()
    override val downloadedFutureWeather: LiveData<FutureWeatherResponse>
        get() = _downloadedFutureWeather

    override suspend fun fetchFutureWeather(location: String, languageCode: String) {
        try {
            val fetchedFutureWeather = apixuWeatherApiService
                .getFutureWeather(location, 7, languageCode)
                .await()
            _downloadedFutureWeather.postValue(fetchedFutureWeather)

        }catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}