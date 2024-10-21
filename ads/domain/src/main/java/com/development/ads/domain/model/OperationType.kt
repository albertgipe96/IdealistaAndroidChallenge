package com.development.ads.domain.model

enum class OperationType {
    SALE,
    RENT,
    UNKNOWN
}

fun mapToOperationType(value: String): OperationType {
    return when (value) {
        "sale" -> OperationType.SALE
        "rent" -> OperationType.RENT
        else -> OperationType.UNKNOWN
    }
}