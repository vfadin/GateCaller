package com.gatecaller.ui.home

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gatecaller.domain.entity.Contact

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    event: (HomeScreenEvent) -> Unit
) {
    val state by viewModel.contactState.observeAsState(null)
    val openDialog = remember { mutableStateOf(false) }
    Column {
        TopAppBar {
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = {
                openDialog.value = true
            }) {
                Icon(imageVector = Icons.Filled.AddCircleOutline, contentDescription = null)
            }
        }
        Row {
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    buttons = {
                        Column(
                            modifier = Modifier.padding(all = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { event(HomeScreenEvent.OnNewContactClick) }
                            ) {
                                Text("Новый контакт")
                            }
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { event(HomeScreenEvent.OnExistContactClick) }
                            ) {
                                Text("Существующий контакт")
                            }
                        }
                    }
                )
            }
            state?.let {
                NumbersList(dataList = it, event)
            }
        }
    }

}

@Composable
fun NumberCard(contact: Contact, event: (HomeScreenEvent) -> Unit) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { event(HomeScreenEvent.OnItemClick(contact.number)) },
                    onLongPress = {
                        isMenuExpanded = true
                    }
                )
            }
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
            DropdownMenu(expanded = isMenuExpanded, onDismissRequest = { isMenuExpanded = false }) {
                DropdownMenuItem(
                    onClick = { event(HomeScreenEvent.OnDeleteClick(contact.id)) },
                    Modifier.wrapContentWidth()
                ) {
                    Icon(Icons.Filled.Delete, null)
                    Text(
                        text = "Удалить",
                        Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun NumbersList(dataList: List<Contact>, event: (HomeScreenEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        items(dataList) { number ->
            NumberCard(contact = number, event)
        }
    }
}