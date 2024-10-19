package com.development.ads.data.model

import com.development.ads.domain.model.AdAddressInfo
import com.development.ads.domain.model.AdData
import com.development.ads.domain.model.AdFeatures
import com.development.ads.domain.model.AdImage
import com.development.ads.domain.model.AdImageTag
import com.development.ads.domain.model.AdMultimedia
import com.development.ads.domain.model.OperationType
import com.development.ads.domain.model.PropertyType

data class RemoteAd(
    val propertyCode: String,
    val thumbnail: String,
    val floor: String,
    val priceInfo: RemotePriceInfo,
    val propertyType: String,
    val operation: String,
    val size: Long,
    val exterior: Boolean,
    val rooms: Int,
    val bathrooms: Int,
    val address: String,
    val province: String,
    val municipality: String,
    val district: String,
    val country: String,
    val neighborhood: String,
    val latitude: Long,
    val longitude: Long,
    val description: String,
    val multimedia: RemoteAdMultimedia,
    val features: RemoteAdFeatures
) {
    fun toAdData(): AdData {
        return AdData(
            propertyCode = propertyCode.toInt(),
            thumbnail = thumbnail,
            floor = floor.toInt(),
            priceInEuros = priceInfo.price.amount,
            propertyType = mapToPropertyType(propertyType),
            operation = mapToOperationType(operation),
            sizeInMeters = size,
            isExterior = exterior,
            roomsNumber = rooms,
            bathroomsNumber = bathrooms,
            addressInfo = AdAddressInfo(
                address = address,
                province = province,
                municipality = municipality,
                district = district,
                country = country,
                neighborhood = neighborhood,
                latitude = latitude,
                longitude = longitude
            ),
            description = description,
            multimedia = AdMultimedia(
                images = multimedia.images.map {
                    AdImage(
                        url = it.url,
                        tag = mapToAdImageTag(it.tag)
                    )
                }
            ),
            features = AdFeatures(
                hasAirConditioning = features.hasAirConditioning,
                hasBoxRoom = features.hasBoxRoom
            )
        )
    }

    private fun mapToPropertyType(value: String): PropertyType {
        return when (value) {
            "flat" -> PropertyType.FLAT
            "house" -> PropertyType.HOUSE
            "chalet" -> PropertyType.CHALET
            else -> PropertyType.UNKNOWN
        }
    }

    private fun mapToOperationType(value: String): OperationType {
        return when (value) {
            "sale" -> OperationType.SALE
            "rent" -> OperationType.RENT
            else -> OperationType.UNKNOWN
        }
    }

    private fun mapToAdImageTag(value: String): AdImageTag {
        return when (value) {
            "livingRoom" -> AdImageTag.LIVING_ROOM
            "views" -> AdImageTag.VIEWS
            "facade" -> AdImageTag.FACADE
            "corridor" -> AdImageTag.CORRIDOR
            "bedroom" -> AdImageTag.BEDROOM
            "kitchen" -> AdImageTag.KITCHEN
            "bathroom" -> AdImageTag.BATHROOM
            else -> AdImageTag.UNKNOWN
        }
    }
}

data class RemotePriceInfo(
    val price: RemotePrice
)

data class RemotePrice(
    val amount: Long,
    val currencySuffix: String
)

data class RemoteAdMultimedia(
    val images: List<RemoteAdImage>
)

data class RemoteAdImage(
    val url: String,
    val tag: String
)

data class RemoteAdFeatures(
    val hasAirConditioning: Boolean,
    val hasBoxRoom: Boolean
)