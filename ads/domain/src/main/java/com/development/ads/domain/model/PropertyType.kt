package com.development.ads.domain.model

enum class PropertyType {
    FLAT,
    HOUSE,
    CHALET,
    UNKNOWN
}

fun mapToPropertyType(value: String): PropertyType {
    return when (value) {
        "flat" -> PropertyType.FLAT
        "house" -> PropertyType.HOUSE
        "chalet" -> PropertyType.CHALET
        else -> PropertyType.UNKNOWN
    }
}