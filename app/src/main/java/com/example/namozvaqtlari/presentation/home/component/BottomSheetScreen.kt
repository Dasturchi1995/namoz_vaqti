package com.example.namozvaqtlari.presentation.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen() {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }
//    val coroutineScope = rememberCoroutineScope()
//    var isSheetOpen by remember { mutableStateOf(false) }
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = {isSheetOpen = true}) {
//                Icon(
//                    imageVector = Icons.Default.LocationOn,
//                    contentDescription = ""
//                )
//            }
//        }
//    ) {
//    }

    ModalBottomSheet(
        onDismissRequest = {showBottomSheet = false},
        sheetState = sheetState,
        dragHandle = {BottomSheetDefaults.DragHandle()}
    ) {
        Column(
            modifier = Modifier
                 .fillMaxSize()
        ) {
            Text("BottomSheet")
            Button(
                onClick = {showBottomSheet = false}
            ) {
                Text("Close")
            }
        }
    }

}

@Preview
@Composable
private fun Preview() {
    BottomSheetScreen()
}