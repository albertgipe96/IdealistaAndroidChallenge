package com.development.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.development.core.data.database.entitiy.FavAdEntity

@Dao
interface FavAdsDao {
    @Query("SELECT * FROM favAds")
    suspend fun getFavAdsList(): List<FavAdEntity>

    @Query("SELECT * FROM favAds WHERE adId=:id")
    suspend fun getFavAd(id: Int): List<FavAdEntity>

    @Upsert
    suspend fun upsertFavAd(favAd: FavAdEntity)

    @Query("DELETE FROM favAds WHERE adId=:id")
    suspend fun deleteFavAd(id: Int)
}