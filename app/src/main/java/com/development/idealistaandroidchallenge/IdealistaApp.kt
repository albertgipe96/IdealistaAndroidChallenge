package com.development.idealistaandroidchallenge

import android.app.Application
import com.development.ads.data.di.adsDataModule
import com.development.ads.presentation.di.adsPresentationModule
import com.development.core.data.di.coreDataModule
import com.development.idealistaandroidchallenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class IdealistaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger()
            androidContext(this@IdealistaApp)
            modules(
                appModule,
                coreDataModule,
                adsPresentationModule,
                adsDataModule
            )
        }
    }
}