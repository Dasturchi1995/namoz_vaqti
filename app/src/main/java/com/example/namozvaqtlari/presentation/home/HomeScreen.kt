package com.example.namozvaqtlari.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.namozvaqtlari.R
import com.example.namozvaqtlari.Screen
import com.example.namozvaqtlari.common.SharedPref
import com.example.namozvaqtlari.common.Util
import com.example.namozvaqtlari.presentation.home.component.CustomDialog
import com.example.namozvaqtlari.presentation.home.component.PrayingEvent
import com.example.namozvaqtlari.presentation.home.component.PrayingState
import com.example.namozvaqtlari.ui.Title
import com.example.namozvaqtlari.ui.TitleBig
import com.example.namozvaqtlari.ui.theme.PrimerColor
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    state: PrayingState,
    event: (PrayingEvent) -> Unit,
    navController: NavHostController
) {


    LaunchedEffect(true) {
        if (SharedPref.regionIndex >= 0) {
            val region = Util.getUzbekistanRegions().get(SharedPref.regionIndex)
            event(PrayingEvent.SetRegion(region))
        }
        event(PrayingEvent.RequestDailyPraying)
    }
    var nextPrayingTime by remember {
        mutableStateOf(Util.findNextPrayerTime(state))
    }
    var remainingTime by remember {
        mutableStateOf(Util.calculateRemainingTime(nextPrayingTime.second))
    }


    LaunchedEffect(state) {
        while (true) {
            nextPrayingTime = Util.findNextPrayerTime(state)
            remainingTime = Util.calculateRemainingTime(nextPrayingTime.second)
            delay(1000)

            println("SSS nextPrayingTime=$nextPrayingTime")
            println("SSS remainingTime=$remainingTime")
        }
    }

    var showRegionBottomSheet by remember {
        mutableStateOf(false)
    }
    var showLocationDialog by remember {
        mutableStateOf(false)
    }
    if (showRegionBottomSheet) {
        SelectRegionBottomSheet(
            onSelect = { region ->
                event(PrayingEvent.SetRegion(region))
            },
            onDismiss = {
                showRegionBottomSheet = false
            }
        )
    }

    LaunchedEffect(state.region) {
        showLocationDialog = state.region == null
    }

    if (showLocationDialog) {
        CustomDialog(
            title = stringResource(R.string.xудудни_танланг),
            message = stringResource(R.string.намоз_вақтларини_олиш_учун_xудудингизни_танланг),
            image = Icons.Default.LocationOn,
            buttonText = stringResource(R.string.танлаш),
            onDismiss = {
                showLocationDialog = false
            },
            onConfirm = {
                showRegionBottomSheet = true
            }
        )
    }

    if (state.loading) {
        Dialog(
            onDismissRequest = {}
        ) {
            CircularProgressIndicator()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimerColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = nextPrayingTime.first,
                fontSize = Title,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(Modifier.size(20.dp))
            Text(
                text = nextPrayingTime.second,
                fontSize = TitleBig,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(Modifier.size(10.dp))
            Text(
                remainingTime,
                fontSize = Title,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        TextButton(
            onClick = {
                showRegionBottomSheet = true
            },
            modifier = Modifier
                .align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(25.dp)
            )
            Spacer(Modifier.size(10.dp))
            Text(
                text = state.region?.nameUz ?: stringResource(R.string.xудудни_танланг),
                fontSize = 20.sp,
                color = Color.White
            )
        }
        Spacer(Modifier.size(5.dp))
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .fillMaxSize()
                .weight(1.7f)
                .background(Color.White)
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.PrayerTimesMonthly)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    "Душанба 28 Январ",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 25.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Тонг",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = state.prayingData?.timings?.Fajr ?: "",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Қуёш",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = state.prayingData?.timings?.Sunrise ?: "",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Пешин",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = state.prayingData?.timings?.Dhuhr ?: "",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Аср",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = state.prayingData?.timings?.Asr ?: "",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Шом",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = state.prayingData?.timings?.Maghrib ?: "",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Хуфтон",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = state.prayingData?.timings?.Isha ?: "",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeScreen(
        state = PrayingState(),
        event = {},
        rememberNavController()
    )
}