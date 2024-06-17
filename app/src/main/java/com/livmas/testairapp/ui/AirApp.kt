package com.livmas.testairapp.ui

import android.app.Application
import com.livmas.air_tikets.ui.home.homeModule
import org.koin.core.context.startKoin

class AirApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                homeModule
            )
        }
    }
}