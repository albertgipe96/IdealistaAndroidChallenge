package com.development.idealistaandroidchallenge

import android.app.Application
import com.development.idealistaandroidchallenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class IdealistaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@IdealistaApp)
            modules(
                appModule,
            )
        }
    }
}