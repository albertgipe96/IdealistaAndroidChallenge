package com.development.ads.data

import android.database.sqlite.SQLiteFullException
import com.development.ads.domain.FavAdsRepository
import com.development.ads.domain.model.FavoritedInfo
import com.development.core.data.database.dao.FavAdsDao
import com.development.core.data.database.entitiy.FavAdEntity
import com.development.core.domain.result.DataError
import com.development.core.domain.result.EmptyDataResult
import com.development.core.domain.result.Result

class RoomFavAdsRepository(
    private val favAdsDao: FavAdsDao
) : FavAdsRepository {

    override suspend fun getFavoritedInfoList(): List<FavoritedInfo> {
        return favAdsDao.getFavAdsList().map { it.toFavoritedInfo() }
    }

    override suspend fun getFavoritedInfo(id: Int): FavoritedInfo? {
        val favAds = favAdsDao.getFavAd(id)
        if (favAds.isNotEmpty()) return favAds.first().toFavoritedInfo()
        return null
    }

    override suspend fun saveAdToFavorites(id: Int): Result<FavoritedInfo, DataError.Local> {
        return try {
            val dateInMillis = System.currentTimeMillis()
            favAdsDao.upsertFavAd(FavAdEntity(id, dateInMillis))
            Result.Success(FavoritedInfo(id, true, dateInMillis))
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFavoritedAd(id: Int): EmptyDataResult<DataError.Local> {
        return try {
            favAdsDao.deleteFavAd(id)
            Result.Success(Unit)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    private fun FavAdEntity.toFavoritedInfo(): FavoritedInfo {
        return FavoritedInfo(
            adId = adId,
            isFavorited = true,
            date = dateInMillis
        )
    }

}