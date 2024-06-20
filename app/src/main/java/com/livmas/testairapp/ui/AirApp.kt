package com.livmas.testairapp.ui

import android.app.Application
import com.livmas.search.homeModule
import com.livmas.core.dataCoreModule
import com.livmas.search.dataSearchModule
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
                dataCoreModule,
                dataSearchModule
            )
        }
    }
}