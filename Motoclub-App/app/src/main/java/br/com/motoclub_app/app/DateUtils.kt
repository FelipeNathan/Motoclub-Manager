package br.com.motoclub_app.app

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {

        private val uiFormat = SimpleDateFormat("dd/MM/yyyy")
        private val imgNameFormat = SimpleDateFormat("ddMMyyyy")

        fun calendarToString(calendar: Calendar) = uiFormat.format(calendar.time)

        fun stringToCalendar(str: String): Calendar {

            val date = uiFormat.parse(str)
            val calendar = Calendar.getInstance()

            calendar.time = date

            return calendar
        }

        fun calendarToImageName(calendar: Calendar) = imgNameFormat.format(calendar.time)
    }
}