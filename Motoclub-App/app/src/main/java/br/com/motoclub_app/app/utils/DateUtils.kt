package br.com.motoclub_app.app.utils

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {

        private val uiFormat = SimpleDateFormat("dd/MM/yyyy")
        private val imgNameFormat = SimpleDateFormat("ddMMyyyy")

        fun timestampToString(timestamp: Timestamp): String = uiFormat.format(timestamp.toDate())

        fun stringToTimestamp(str: String): Timestamp {

            val date = uiFormat.parse(str)
            return Timestamp(date!!)
        }

        fun calendarToImageName(calendar: Calendar): String = imgNameFormat.format(calendar.time)
    }
}