package com.development.ads.presentation.di

import com.development.ads.presentation.detail.AdDetailViewModel
import com.development.ads.presentation.list.AdsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val adsPresentationModule = module {
    viewModelOf(::AdsListViewModel)
    viewModelOf(::AdDetailViewModel)
}