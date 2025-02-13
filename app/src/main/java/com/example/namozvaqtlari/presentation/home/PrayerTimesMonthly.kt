package com.example.namozvaqtlari.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.namozvaqtlari.R
import com.example.namozvaqtlari.common.Util
import com.example.namozvaqtlari.presentation.home.component.PrayingEvent
import com.example.namozvaqtlari.presentation.home.component.PrayingState
import com.example.namozvaqtlari.presentation.home.component.PrayingTimeItem
import com.example.namozvaqtlari.ui.itemHeight
import com.example.namozvaqtlari.ui.itemTextSize

@Composable
fun PrayerTimesMonthly(
    navController: NavHostController,
    state: PrayingState,
    event: (PrayingEvent) -> Unit
) {

    LaunchedEffect(true) {
        event(PrayingEvent.RequestMonthlyPraying)
    }
    if (state.loading) {
        Dialog(
            onDismissRequest = {}
        ) {
            CircularProgressIndicator()
        }
    }
    val image = ImageBitmap.imageResource(id = R.drawable.fon_green)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .drawBehind {
                    drawImage(
                        image,
                        dstSize = IntSize(size.width.toInt(), size.height.toInt())
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }

            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Намоз вақтлари",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }




        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${Util.getMonthName()} / ${Util.getThisYear()}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }


        Row(
            modifier = Modifier
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
                    text = "День",
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
                    text = "Бомдод",
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
                    text = "Қуёш",
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
                    text = "Пешин",
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
                    text = "Аср",
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
                    text = "Шом",
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
                    text = "Xуфтон",
                    fontSize = itemTextSize,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }







        }

        LazyColumn {
            itemsIndexed(state.prayingDataList){ index, data->
                PrayingTimeItem(
                    prayingData = data,
                    day = index+1
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PrayerTimesMonthly(
        rememberNavController(),
        PrayingState(),
        {}
    )
}