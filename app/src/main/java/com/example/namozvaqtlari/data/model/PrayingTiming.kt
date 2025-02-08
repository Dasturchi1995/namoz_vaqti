package com.example.ibroxim.model

data class PrayingTiming(
    val Fajr:String = "",       // Bomdod
    val Sunrise:String = "",    //Quyosh chiqishi
    val Dhuhr:String = "",      //Peshin
    val Asr:String = "",        //Asr
    val Sunset:String = "",     //Quyosh botishi
    val Maghrib:String = "",    //Shom
    val Isha:String = "",       //Hufton
    val Imsak:String = "",
    val Midnight:String = "",
    val Firstthird:String = "",
    val Lastthird:String = "",
)
