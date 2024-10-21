package com.development.ads.data.di

import com.development.ads.data.KtorAdsRepository
import com.development.ads.data.RoomFavAdsRepository
import com.development.ads.domain.AdsRepository
import com.development.ads.domain.FavAdsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val adsDataModule = module {
    singleOf(::KtorAdsRepository).bind<AdsRepository>()
    singleOf(::RoomFavAdsRepository).bind<FavAdsRepository>()
}