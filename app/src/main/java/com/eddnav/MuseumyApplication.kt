package com.eddnav

import android.app.Application
import com.eddnav.museumy.di.Koin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MuseumyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MuseumyApplication)
            modules(Koin.modules)
        }
    }
}