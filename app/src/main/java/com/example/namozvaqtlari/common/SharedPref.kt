package com.example.namozvaqtlari.common

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.enumpref.enumValuePref
import com.example.namozvaqtlari.data.model.Region

object SharedPref: KotprefModel() {
    var regionIndex by intPref(-1)
}