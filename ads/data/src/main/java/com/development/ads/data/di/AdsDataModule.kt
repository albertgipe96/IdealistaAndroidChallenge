package com.development.ads.data.di

import com.development.ads.data.KtorAdsRepository
import com.development.ads.domain.AdsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val adsDataModule = module {
    singleOf(::KtorAdsRepository).bind<AdsRepository>()
}