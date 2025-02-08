package com.example.namozvaqtlari.presentation.home.component

import com.example.ibroxim.model.DailyResponse
import com.example.ibroxim.model.MonthlyResponse
import com.example.ibroxim.model.Praying
import com.example.ibroxim.model.PrayingData
import com.example.namozvaqtlari.data.model.Region

data class PrayingState(
    val loading: Boolean = false,
    val errorMessage: String = "",
    val prayingData: PrayingData? = null,
    val ertangiBomdodVaqti:String = "",
    val prayingDataList: List<PrayingData> = emptyList(),
    val region:Region? = null
)
