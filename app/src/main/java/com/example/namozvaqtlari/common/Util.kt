package com.example.namozvaqtlari.common

import com.example.ibroxim.model.PrayingData
import com.example.namozvaqtlari.data.model.Region
import com.example.namozvaqtlari.presentation.home.component.PrayingState
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Util {

    fun getTodayDate():String{
        val todayDateInMillis = System.currentTimeMillis()
        return reformatDate(todayDateInMillis)
    }
    fun getTomorrowDate():String{
        val tomorrowDate = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        return reformatDate(tomorrowDate)
    }

    fun getThisYear():String{
        return LocalDate.now().year.toString()
    }
    fun getThisMonth():String{
        return LocalDate.now().monthValue.toString()
    }
    fun getMonthName(): String {
        return Month.of(getThisMonth().toInt()).getDisplayName(TextStyle.FULL, Locale("UZ")).uppercase()
    }
    fun reformatDate(dateTimeInMillis:Long):String{
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val date = Date(dateTimeInMillis)
        return formatter.format(date)
    }

    fun getCurrentDateInKrill(): String {
        val monthsKrill = listOf(
            "Январ", "Феврал", "Март", "Апрел", "Май", "Июнь",
            "Июль", "Август", "Сентябр", "Октябр", "Ноябр", "Декабр"
        )

        val weekdaysKrill = listOf(
            "Якшанба", "Душанба", "Сешанба", "Чоршанба", "Пайшанба", "Жума", "Шанба"
        )

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val monthIndex = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val weekDayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1 // Calendar.DAY_OF_WEEK starts from 1 (Sunday)

        return "${weekdaysKrill[weekDayIndex]}, $day-${monthsKrill[monthIndex]}-$year"
    }

    fun getUzbekistanRegions(): List<Region> {
        val regions = listOf(
            Region("Тошкент", "Tashkent", 41.2995, 69.2401),
            Region("Самарқанд", "Samarkand region", 39.6542, 66.9759),
            Region("Бухоро", "Bukhara region", 39.7747, 64.4286),
            Region("Хоразм", "Khorezm region", 41.5500, 60.6333),
            Region("Андижон", "Andijan region", 40.7833, 72.3333),
            Region("Фарғона", "Fergana region", 40.3864, 71.7864),
            Region("Қўқон шаҳри", "Kokand", 40.533223416103745, 70.93112400614909),
            Region("Наманган", "Namangan region", 40.9983, 71.6726),
            Region("Навоий", "Navoi region", 40.0844, 65.3792),
            Region("Қашқадарё", "Kashkadarya region", 38.8606, 65.7891),
            Region("Сурхондарё", "Surkhandarya region", 37.9400, 67.5700),
            Region("Жиззах", "Jizzakh region", 40.1158, 67.8422),
            Region("Сирдарё", "Sirdarya", 40.5014, 68.7550),
            Region("Қорақалпоғистон", "Karakalpakstan", 43.7000, 59.0000),
            Region("Тошкент вилояти", "Tashkent Region", 41.0167, 69.0000)
        )
        return regions
    }

    fun findNextPrayerTime(state: PrayingState): Pair<String, String> {

        println("findNextPrayerTime date=${state.prayingData}")

        if (state.prayingData == null){
            return Pair("","")
        }
        val timing = state.prayingData.timings

        var prayerTimes = listOf<Pair<String, String>>(
            Pair("Бомдод", timing.Fajr),
            Pair("Пешин", timing.Dhuhr),
            Pair("Аср", timing.Asr),
            Pair("Шом", timing.Maghrib),
            Pair("Ҳуфтон", timing.Isha),
        )


        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val now = LocalTime.now()

        for (time in prayerTimes) {
            val prayerTime = LocalTime.parse(time.second, formatter)
            if (prayerTime.isAfter(now)) {
                return time // Keyingi namozni topdi
            }
        }

        // Agar joriy vaqt oxirgi namozdan keyin bo‘lsa, ertangi birinchi namozni tanlash
        return Pair("Бомдод", state.ertangiBomdodVaqti)
    }

    fun calculateRemainingTime(nextPrayerTime: String): String {
        if (nextPrayerTime.isEmpty()){
            return ""
        }
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val now = LocalDateTime.now()
        var todayPrayerTime = LocalTime.parse(nextPrayerTime, formatter)

        //converting todayPrayerTime to prayerDateTime
        var prayerDateTime = LocalDateTime.of(now.toLocalDate(), todayPrayerTime)

        if (prayerDateTime.isBefore(now)) {
            prayerDateTime = prayerDateTime.plusDays(1)
        }

        val duration = Duration.between(now, prayerDateTime)
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60
        val seconds = duration.seconds % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

}