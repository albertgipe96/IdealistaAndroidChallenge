package com.development.ads.data.model

import com.development.ads.domain.model.AdAddressInfo
import com.development.ads.domain.model.AdData
import com.development.ads.domain.model.AdFeatures
import com.development.ads.domain.model.AdImage
import com.development.ads.domain.model.AdImageTag
import com.development.ads.domain.model.AdMultimedia
import com.development.ads.domain.model.OperationType
import com.development.ads.domain.model.PropertyType
import kotlinx.serialization.Serializable

@Serializable
data class RemoteAdDetail(
    val adid: Int,
    val price: Double,
    val operation: String, //OperationType
    val propertyType: String, //PropertyType
    val extendedPropertyType: String, //PropertyType
    val multimedia: RemoteAdMultimedia,
    val propertyComment: String,
    val ubication: RemoteAdUbication,
    val moreCharacteristics: RemoteMoreCharacteristics
) {
    fun toAdData(): AdData {
        return AdData(
            propertyCode = adid,
            thumbnail = multimedia.images.first().url,
            floor = moreCharacteristics.floor.toInt(),
            priceInEuros = price.toLong(),
            propertyType = mapToPropertyType(propertyType),
            operation = mapToOperationType(operation),
            sizeInMeters = 0,
            isExterior = moreCharacteristics.exterior,
            roomsNumber = moreCharacteristics.roomNumber,
            bathroomsNumber = moreCharacteristics.bathNumber,
            addressInfo = AdAddressInfo(
                address = "",
                province = "",
                municipality = "",
                district = "",
                country = "",
                neighborhood = "",
                latitude = ubication.latitude.toLong(),
                longitude = ubication.longitude.toLong()
            ),
            description = propertyComment,
            multimedia = AdMultimedia(
                images = multimedia.images.map {
                    AdImage(
                        url = it.url,
                        tag = mapToAdImageTag(it.tag)
                    )
                }
            ),
            features = AdFeatures(
                hasAirConditioning = false,
                hasBoxRoom = false
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

@Serializable
data class RemoteAdUbication(
    val latitude: Double,
    val longitude: Double,
)

@Serializable
data class RemoteMoreCharacteristics(
    val communityCosts: Double,
    val roomNumber: Int,
    val bathNumber: Int,
    val exterior: Boolean,
    val housingFurnitures: String,
    val agencyIsABank: Boolean,
    val energyCertificationType: String,
    val flatLocation: String,
    val lift: Boolean,
    val floor: String
)