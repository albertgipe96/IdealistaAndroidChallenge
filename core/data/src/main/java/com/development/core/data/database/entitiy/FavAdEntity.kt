package com.development.core.data.database.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favAds")
data class FavAdEntity(
    @PrimaryKey(autoGenerate = false) val adId: Int,
    val dateInMillis: Long
)