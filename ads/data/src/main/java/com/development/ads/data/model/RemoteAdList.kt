package com.development.ads.data.model

import com.development.ads.domain.model.AdData
import com.development.ads.domain.model.AdImage
import com.development.ads.domain.model.AdImageTag
import com.development.ads.domain.model.AdSpecs
import com.development.ads.domain.model.OperationType
import com.development.ads.domain.model.PropertyCharacteristics
import com.development.ads.domain.model.PropertySpecs
import com.development.ads.domain.model.PropertyType
import kotlinx.serialization.Serializable

@Serializable
data class RemoteAdList(
    val propertyCode: String,
    val thumbnail: String,
    val floor: String,
    val price: Double,
    val propertyType: String,
    val operation: String,
    val size: Double,
    val exterior: Boolean,
    val rooms: Int,
    val bathrooms: Int,
    val address: String,
    val province: String,
    val municipality: String,
    val district: String,
    val country: String,
    val neighborhood: String,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val multimedia: RemoteAdMultimedia,
    val features: RemoteAdFeatures
) {
    fun toAdData(): AdData {
        return AdData(
            adId = propertyCode.toInt(),
            thumbnail = thumbnail,
            adSpecs = AdSpecs(
                price = price.toLong(),
                operation = mapToOperationType(operation)
            ),
            propertySpecs = PropertySpecs(
                fullAddress = address,
                municipality = municipality,
                country = country,
                latitude = latitude.toLong(),
                longitude = longitude.toLong(),
                characteristics = PropertyCharacteristics(
                    propertyType = mapToPropertyType(propertyType),
                    hasAirConditioning = features.hasAirConditioning,
                    hasBoxRoom = features.hasBoxRoom
                )
            ),
            images = multimedia.images.map {
                AdImage(
                    url = it.url,
                    tag = mapToAdImageTag(it.tag)
                )
            }
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

@Serializable
data class RemoteAdMultimedia(
    val images: List<RemoteAdImage>
)

@Serializable
data class RemoteAdImage(
    val url: String,
    val tag: String,
    val localizedName: String? = null
)

@Serializable
data class RemoteAdFeatures(
    val hasAirConditioning: Boolean,
    val hasBoxRoom: Boolean
)