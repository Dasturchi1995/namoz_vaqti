package com.example.namozvaqtlari.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ibroxim.model.PrayingData
import com.example.ibroxim.model.PrayingDate
import com.example.ibroxim.model.PrayingTiming
import com.example.namozvaqtlari.ui.itemHeight
import com.example.namozvaqtlari.ui.itemTextSize

@Composable
fun PrayingTimeItem(
    modifier: Modifier = Modifier,
    prayingData: PrayingData,
    day:Int,
) {

    val bg = if (day % 2 == 0) {
        Color.White
    }
    else{
        Color.LightGray
    }

    Row(
        modifier = Modifier
            .background(bg)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(itemHeight),
            contentAlignment = Alignment.Center
        ){
            Text(
                fontWeight = FontWeight.Bold,
                text = "$day",
                fontSize = itemTextSize,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .height(itemHeight),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = prayingData.timings.Fajr,
                fontSize = itemTextSize,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .height(itemHeight),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = prayingData.timings.Sunrise,
                fontSize = itemTextSize,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .height(itemHeight),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = prayingData.timings.Dhuhr,
                fontSize = itemTextSize,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .height(itemHeight),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = prayingData.timings.Asr,
                fontSize = itemTextSize,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .height(itemHeight),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = prayingData.timings.Maghrib,
                fontSize = itemTextSize,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .height(itemHeight),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = prayingData.timings.Isha,
                fontSize = itemTextSize,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun Pre() {
    PrayingTimeItem(
        prayingData = PrayingData(
            timings = PrayingTiming(),
            date = PrayingDate()
        ),
        day = 1
    )
}