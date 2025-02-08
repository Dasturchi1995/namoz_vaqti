package com.example.namozvaqtlari.common

import com.example.ibroxim.model.PrayingData
import com.example.namozvaqtlari.data.model.Region
import com.example.namozvaqtlari.presentation.home.component.PrayingState
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

object Util {

    fun getTodayDate():String{
        val todayDateInMillis = System.currentTimeMillis()
        return reformatDate(todayDateInMillis)
    }
    fun getTomorrowDate():String{
        val tomorrowDate = LocalDate.now().plusDays(1).toEpochDay()
        return reformatDate(tomorrowDate)
    }

    fun reformatDate(dateTimeInMillis:Long):String{
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val date = Date(dateTimeInMillis)
        return formatter.format(date)
    }

    fun getUzbekistanRegions(): List<Region> {
        return listOf(
            Region("Андижон", "Andijan"),
            Region("Бухоро", "Bukhara"),
            Region("Фарғона", "Fergana"),
            Region("Жиззах", "Jizzakh"),
            Region("Хоразм", "Khorezm"),
            Region("Наманган", "Namangan"),
            Region("Навоий", "Navoiy"),
            Region("Қашқадарё", "Kashkadarya"),
            Region("Қорақалпоғистон", "Karakalpakstan"),
            Region("Самарқанд", "Samarkand"),
            Region("Сирдарё", "Syrdarya"),
            Region("Сурхондарё", "Surkhandarya"),
            Region("Тошкент", "Tashkent")
        )
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
        val now = LocalTime.now()
        val prayerTime = LocalTime.parse(nextPrayerTime, formatter)

        val duration = if (prayerTime.isAfter(now)) {
            Duration.between(now, prayerTime)
        } else {
            Duration.between(now, prayerTime.plusHours(24)) // Ertangi namoz uchun
        }

        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60
        val seconds = duration.seconds % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

}