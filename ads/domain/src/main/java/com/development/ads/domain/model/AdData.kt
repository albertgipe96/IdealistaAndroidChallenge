package com.development.ads.domain.model

data class AdData(
    val adId: Int,
    val thumbnail: String,
    val adSpecs: AdSpecs,
    val propertySpecs: PropertySpecs,
    val images: List<AdImage>,
    val favoritedInfo: FavoritedInfo = FavoritedInfo(adId)
)

data class AdSpecs(
    val price: Long,
    val operation: OperationType
)

data class PropertySpecs(
    val fullAddress: String,
    val municipality: String,
    val country: String,
    val latitude: Long,
    val longitude: Long,
    val characteristics: PropertyCharacteristics
)

data class PropertyCharacteristics(
    val propertyType: PropertyType? = null,
    val hasAirConditioning: Boolean? = null,
    val hasBoxRoom: Boolean? = null,
    val communityCosts: Double? = null,
    val roomNumber: Int? = null,
    val bathNumber: Int? = null,
    val exterior: Boolean? = null
)

data class AdImage(
    val url: String,
    val tag: AdImageTag
)

data class FavoritedInfo(
    val adId: Int,
    val isFavorited: Boolean = false,
    val date: Long? = null
)