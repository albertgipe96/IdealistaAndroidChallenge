package com.development.ads.domain

import com.development.ads.domain.model.FavoritedInfo
import com.development.core.domain.result.DataError
import com.development.core.domain.result.EmptyDataResult
import com.development.core.domain.result.Result

interface FavAdsRepository {
    suspend fun getFavoritedInfoList(): List<FavoritedInfo>
    suspend fun getFavoritedInfo(id: Int): FavoritedInfo?
    suspend fun saveAdToFavorites(id: Int): Result<FavoritedInfo, DataError.Local>
    suspend fun deleteFavoritedAd(id: Int): EmptyDataResult<DataError.Local>
}