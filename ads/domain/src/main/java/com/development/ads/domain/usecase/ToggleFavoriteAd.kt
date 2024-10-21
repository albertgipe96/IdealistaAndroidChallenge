package com.development.ads.domain.usecase

import com.development.ads.domain.FavAdsRepository
import com.development.ads.domain.model.FavoritedInfo
import com.development.core.domain.result.DataError
import com.development.core.domain.result.Result

class ToggleFavoriteAd(
    private val favAdsRepository: FavAdsRepository
) {

    suspend operator fun invoke(adId: Int, isFavorited: Boolean): Result<FavoritedInfo, DataError.Local> {
        return when (isFavorited) {
            true -> {
                when (val result = favAdsRepository.deleteFavoritedAd(id = adId)) {
                    is Result.Error -> result
                    is Result.Success -> Result.Success(FavoritedInfo(adId = adId))
                }
            }
            false -> favAdsRepository.saveAdToFavorites(id = adId)
        }
    }

}