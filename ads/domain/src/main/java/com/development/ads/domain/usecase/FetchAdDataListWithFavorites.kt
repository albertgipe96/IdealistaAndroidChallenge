package com.development.ads.domain.usecase

import com.development.ads.domain.AdsRepository
import com.development.ads.domain.FavAdsRepository
import com.development.ads.domain.model.AdData
import com.development.core.domain.result.DataError
import com.development.core.domain.result.Result

class FetchAdDataListWithFavorites(
    private val adsRepository: AdsRepository,
    private val favAdsRepository: FavAdsRepository
) {

    suspend operator fun invoke(): Result<List<AdData>, DataError.Network> {
        val result = adsRepository.fetchAdsData()
        when (result) {
            is Result.Error -> return result
            is Result.Success -> {
                val favInfoList = favAdsRepository.getFavoritedInfoList()
                val adsList = result.data.map { ad ->
                    if (ad.adId in favInfoList.map { it.adId }) {
                        ad.copy(favoritedInfo = favInfoList.first { it.adId == ad.adId })
                    } else ad
                }
                return Result.Success(adsList)
            }
        }
    }

}