package com.example.namozvaqtlari.presentation.home.component

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
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.namozvaqtlari.ui.Title
import com.example.namozvaqtlari.ui.TitleBig
import com.example.namozvaqtlari.ui.theme.PrimerColor

@Composable
fun HomeScreen() {
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
                "Tong",
                fontSize = Title,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(Modifier.size(20.dp))
            Text(
                "06:07",
                fontSize = TitleBig,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(Modifier.size(10.dp))
            Text(
                "-8:20:56",
                fontSize = Title,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        Column(
            modifier = Modifier
//                .weight(0.2f)
        ) {  }
        TextButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.Start),
        ) {
            Icon(
                imageVector = Icons.Default.Autorenew,
                contentDescription = "",
                tint = Color.White
            )
            Spacer(Modifier.size(5.dp))
            Text(
                "Farg`ona viloyati O`zbegiston",
                fontSize = 17.sp,
                color = Color.White
            )
        }
        Column (
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .fillMaxSize()
                .weight(1.7f)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBackIos,
                        contentDescription = ""
                    )
                }
                Column(
                    modifier = Modifier
                        .clickable {  },
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        "Душанба 28 Январ",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 25.sp,
                        color = Color.Black
                    )
                    Text(
                        "27 Ражаб 1446 й",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                        contentDescription = ""
                    )
                }
            }
            Spacer(Modifier.size(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Тонг",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    "06:07",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Қуёш",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    "07:30",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Пешин",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    "12:30",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Асир",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    "15:47",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Шом",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    "17:33",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Хуфтон",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    "18:46",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeScreen()
}