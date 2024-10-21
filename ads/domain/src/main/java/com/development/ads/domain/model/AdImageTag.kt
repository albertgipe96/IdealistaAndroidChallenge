package com.development.ads.domain.model

enum class AdImageTag {
    LIVING_ROOM,
    VIEWS,
    FACADE,
    CORRIDOR,
    BEDROOM,
    KITCHEN,
    BATHROOM,
    UNKNOWN
}

fun mapToAdImageTag(value: String): AdImageTag {
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