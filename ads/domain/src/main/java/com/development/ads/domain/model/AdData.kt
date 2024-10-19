package com.development.ads.domain.model

data class AdData(
    val propertyCode: Int,
    val thumbnail: String,
    val floor: Int,
    val priceInEuros: Long,
    val propertyType: PropertyType,
    val operation: OperationType,
    val sizeInMeters: Long,
    val isExterior: Boolean,
    val roomsNumber: Int,
    val bathroomsNumber: Int,
    val addressInfo: AdAddressInfo,
    val description: String,
    val multimedia: AdMultimedia,
    val features: AdFeatures
)

data class AdAddressInfo(
    val address: String,
    val province: String,
    val municipality: String,
    val district: String,
    val country: String, //Locale,
    val neighborhood: String,
    val latitude: Long,
    val longitude: Long
)

data class AdMultimedia(
    val images: List<AdImage>
)

data class AdImage(
    val url: String,
    val tag: AdImageTag
)

data class AdFeatures(
    val hasAirConditioning: Boolean,
    val hasBoxRoom: Boolean
)