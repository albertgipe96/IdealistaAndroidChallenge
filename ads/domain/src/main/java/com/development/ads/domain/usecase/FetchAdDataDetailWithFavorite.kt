package com.development.ads.domain.usecase

import com.development.ads.domain.AdsRepository
import com.development.ads.domain.FavAdsRepository
import com.development.ads.domain.model.AdData
import com.development.core.domain.result.DataError
import com.development.core.domain.result.Result

class FetchAdDataDetailWithFavorite(
    private val adsRepository: AdsRepository,
    private val favAdsRepository: FavAdsRepository
) {

    suspend operator fun invoke(adId: Int): Result<AdData, DataError.Network> {
        val result = adsRepository.fetchAdDetail(adId)
        when (result) {
            is Result.Error -> return result
            is Result.Success -> {
                val adData = result.data
                val favInfo = favAdsRepository.getFavoritedInfo(adId)
                return Result.Success(
                    favInfo?.let { adData.copy(favoritedInfo = favInfo) } ?: adData
                )
            }
        }
    }

}