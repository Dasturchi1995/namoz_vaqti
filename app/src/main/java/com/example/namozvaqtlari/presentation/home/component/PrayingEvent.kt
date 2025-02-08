package com.example.namozvaqtlari.presentation.home.component

import com.example.namozvaqtlari.data.model.Region

sealed interface PrayingEvent {
    data object RequestDailyPraying:PrayingEvent
    data object RequestMonthlyPraying:PrayingEvent
    data class SetRegion(val region: Region):PrayingEvent
}