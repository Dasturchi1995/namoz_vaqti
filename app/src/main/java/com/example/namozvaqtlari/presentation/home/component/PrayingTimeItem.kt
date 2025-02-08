package com.example.namozvaqtlari.presentation.home.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ibroxim.model.PrayingData
import com.example.ibroxim.model.PrayingDate
import com.example.ibroxim.model.PrayingTiming
import com.example.namozvaqtlari.ui.itemTextSize

@Composable
fun PrayingTimeItem(
    modifier: Modifier = Modifier,
    prayingData: PrayingData
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Bomdod",
            fontSize = itemTextSize,
            color = Color.Black,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Bomdod",
            fontSize = itemTextSize,
            color = Color.Black,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Bomdod",
            fontSize = itemTextSize,
            color = Color.Black,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Bomdod",
            fontSize = itemTextSize,
            color = Color.Black,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Bomdod",
            fontSize = itemTextSize,
            color = Color.Black,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun Pre() {
    PrayingTimeItem(
        prayingData = PrayingData(
            timings = PrayingTiming(),
            date = PrayingDate()
        )
    )
}