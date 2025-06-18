package com.dmitrychukhnenko.animetracker

import android.app.Application
import com.dmitrychukhnenko.animetracker.di.appModule
import com.dmitrychukhnenko.animetracker.di.dataModule
import com.dmitrychukhnenko.animetracker.di.viewModelModule
import com.dmitrychukhnenko.animetracker.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            modules(appModule, dataModule, domainModule, viewModelModule)
        }
    }
}