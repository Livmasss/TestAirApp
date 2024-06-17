package com.livmas.testairapp.ui

import android.app.Application
import com.livmas.air_tikets.homeModule
import com.livmas.core.dataCoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AirApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AirApp)
            modules(
                homeModule,
                dataCoreModule
            )
        }
    }
}