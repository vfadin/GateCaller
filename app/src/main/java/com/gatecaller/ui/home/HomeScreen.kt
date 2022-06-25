package com.gatecaller.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gatecaller.ui.theme.GateCallerTheme

val dataList = listOf<Pair<String, String>>(
    Pair("123", "Tratata"),
    Pair("223", "ASdatata"),
)

@Composable
fun HomeScreen() {
    Surface {
        NumbersList(dataList = dataList)
    }
}

@Composable
fun NumberCard(number: Pair<String, String>) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(

        ) {
            Text(
                text = number.first,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = number.second,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun NumbersList(dataList: List<Pair<String, String>>) {
    LazyColumn {
        items(dataList) { number ->
            NumberCard(number = number)
        }
    }
}

@Preview(name = "Preview")
@Composable
fun WelcomeScreenPreview() {
    GateCallerTheme {
        HomeScreen()
    }
}