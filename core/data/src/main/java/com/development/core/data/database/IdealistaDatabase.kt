package com.development.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.development.core.data.database.dao.FavAdsDao
import com.development.core.data.database.entitiy.FavAdEntity

@Database(
    entities = [
        FavAdEntity::class
               ],
    version = 1
)
abstract class IdealistaDatabase : RoomDatabase() {
    abstract val favAdsDao: FavAdsDao
}