package com.development.core.data.di

import androidx.room.Room
import com.development.core.data.database.IdealistaDatabase
import com.development.core.data.network.HttpClientFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }
    single {
        Room.databaseBuilder(
            androidApplication(),
            IdealistaDatabase::class.java,
            "idealista.db"
        ).build()
    }
    single { get<IdealistaDatabase>().favAdsDao }
}