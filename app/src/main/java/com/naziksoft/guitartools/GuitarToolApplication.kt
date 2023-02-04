package com.naziksoft.guitartools

import android.app.Application
import com.naziksoft.guitartools.di.appModule
import com.naziksoft.guitartools.view.adapters.Test
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GuitarToolApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startKoin {
            androidContext(this@GuitarToolApplication)
            androidLogger(Level.ERROR)
            modules(appModule)
        }

        Test().Tes()
        val t = Test()
        t.test()
    }
}