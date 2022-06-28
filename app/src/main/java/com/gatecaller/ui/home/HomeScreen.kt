package com.gatecaller.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.gatecaller.domain.entity.Contact
import kotlinx.coroutines.launch

sealed class HomeEvent {
    object Add : HomeEvent()
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    event: (HomeEvent) -> Unit
) {
    val state by viewModel.contactState.observeAsState(null)
    Column {
        TopAppBar {
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { event(HomeEvent.Add) }) {
                Icon(imageVector = Icons.Filled.AddCircleOutline, contentDescription = null)
            }
        }
        Row {
            state?.let {
                println(it)
                NumbersList(dataList = it)
            }
        }
    }

}

@Composable
fun NumberCard(contact: Contact) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column {
            Text(
                text = contact.name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = contact.number,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun NumbersList(dataList: List<Contact>) {
    LazyColumn {
        items(dataList) { number ->
            NumberCard(contact = number)
        }
    }
}

//@Preview(name = "Preview")
//@Composable
//fun WelcomeScreenPreview() {
//    GateCallerTheme {
//            HomeScreen(
//                state = listOf(
//                    Contact(1, "+79995497799", "Gate1"),
//                    Contact(2, "+7913957799", "Gate2"),
//                )
//            )
//    }
//}