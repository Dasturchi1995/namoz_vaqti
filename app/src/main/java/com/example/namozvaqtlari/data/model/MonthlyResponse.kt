package com.example.ibroxim.model

data class MonthlyResponse(
    val code:Int? = null,
    val status: String? = null,
    val data:List<PrayingData>? = null
)

