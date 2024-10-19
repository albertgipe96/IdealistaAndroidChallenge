package com.development.ads.data

import com.development.ads.data.model.RemoteAd
import com.development.ads.domain.AdsRepository
import com.development.ads.domain.model.AdData
import com.development.core.data.network.get
import com.development.core.domain.result.DataError
import com.development.core.domain.result.Result
import com.development.core.domain.result.map
import io.ktor.client.HttpClient

class KtorAdsRepository(
    private val httpClient: HttpClient
) : AdsRepository {

    override suspend fun fetchAdsData(): Result<List<AdData>, DataError.Network> {
        return httpClient.get<List<RemoteAd>>(
            route = "/android-challenge/list.json"
        ).map { it.map { it.toAdData() } }
    }

}