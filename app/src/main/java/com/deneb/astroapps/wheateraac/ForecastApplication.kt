package com.deneb.astroapps.wheateraac

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class ForecastApplication: Application() , KodeinAware{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))
    }
}