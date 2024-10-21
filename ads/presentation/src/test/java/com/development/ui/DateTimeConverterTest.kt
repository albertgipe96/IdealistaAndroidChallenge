package com.development.ui

import com.development.ads.domain.util.DateTimeConverter
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class DateTimeConverterTest {

    @Test
    fun `should return date string equivalent to its millis`() {
        // Given
        val millis = 1609459200000L // 01/01/2021 in millis
        val expectedDateString = "2021-01-01"
        val dateFormat = "yyyy-MM-dd"

        // When
        val actualDateString = DateTimeConverter.millisToDateString(millis, dateFormat)

        // Then
        assertEquals(expectedDateString, actualDateString)
    }

    @Test
    fun `should return date string equivalent to its millis in a new format`() {
        // Given
        val millis = 1609459200000L // 01/01/2021 in millis
        val expectedDateString = "01/01/2021"
        val dateFormat = "dd/MM/yyyy"

        // When
        val actualDateString = DateTimeConverter.millisToDateString(millis, dateFormat)

        // Then
        assertEquals(expectedDateString, actualDateString)
    }

}