package com.development.ads.domain.util

import java.text.SimpleDateFormat
import java.util.Calendar

object DateTimeConverter {

    fun millisToDateString(millis: Long, dateFormatString: String): String {
        val formatter: SimpleDateFormat = SimpleDateFormat(dateFormatString)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return formatter.format(calendar.time)
    }

}