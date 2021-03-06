package com.deneb.astroapps.wheateraac

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.deneb.astroapps.wheateraac.data.ApixuWeatherApiService
import com.deneb.astroapps.wheateraac.data.Repository.ForecastRepository
import com.deneb.astroapps.wheateraac.data.Repository.ForecastRepositoryImpl
import com.deneb.astroapps.wheateraac.data.db.ForecastDatabase
import com.deneb.astroapps.wheateraac.data.network.ConnectivityInterceptor
import com.deneb.astroapps.wheateraac.data.network.ConnectivityInterceptorImpl
import com.deneb.astroapps.wheateraac.data.network.WeatherNetworkDataSource
import com.deneb.astroapps.wheateraac.data.network.WeatherNetworkDataSourceImpl
import com.deneb.astroapps.wheateraac.data.provider.LocationProvider
import com.deneb.astroapps.wheateraac.data.provider.LocationProviderImpl
import com.deneb.astroapps.wheateraac.data.provider.UnitProvider
import com.deneb.astroapps.wheateraac.data.provider.UnitProviderImpl
import com.deneb.astroapps.wheateraac.ui.weather.current.CurrentWeatherViewModelFactory
import com.deneb.astroapps.wheateraac.ui.weather.future.list.FutureListWeatherViewModelFactory
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.Provider
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication: Application() , KodeinAware{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }
        bind() from singleton { instance<ForecastDatabase>().futureWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind() from provider {LocationServices.getFusedLocationProviderClient(instance<Context>())}
        bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance(), instance(), instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
        bind() from provider { FutureListWeatherViewModelFactory(instance(), instance())}
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this,R.xml.preferences, false)
    }
}