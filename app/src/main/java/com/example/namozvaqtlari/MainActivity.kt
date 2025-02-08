package com.example.namozvaqtlari

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.namozvaqtlari.presentation.home.HomeScreen
import com.example.namozvaqtlari.presentation.home.PrayerTimesMonthly
import com.example.namozvaqtlari.presentation.home.component.PrayingViewModel
import com.example.namozvaqtlari.ui.theme.NamozVaqtlariTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NamozVaqtlariTheme {
                val viewModel = hiltViewModel<PrayingViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                val event = viewModel::onEvent

                val navHostController = rememberNavController()

                NavHost(
                    navController = navHostController,
                    startDestination = Screen.HomeScreen
                ){
                    composable<Screen.HomeScreen> {
                        HomeScreen(
                            state = state,
                            event = event,
                            navController = navHostController
                        )
                    }
                    composable<Screen.PrayerTimesMonthly> {
                        PrayerTimesMonthly(
                            navController = navHostController,
                            state = state,
                            event = event
                        )
                    }
                }
            }
        }
    }
}