package com.development.ads.domain

import com.development.ads.domain.model.AdData
import com.development.core.domain.result.DataError
import com.development.core.domain.result.Result

interface AdsRepository {
    suspend fun fetchAdsData(): Result<List<AdData>, DataError.Network>
    suspend fun fetchAdDetail(id: Int): Result<AdData, DataError.Network>
}