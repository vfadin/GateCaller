package com.gatecaller.ui.existContact

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gatecaller.domain.entity.Contact

@Composable
fun ExistContactScreen(
    viewModel: ExistContactViewModel,
    event: (ExistContactEvent) -> Unit
) {
    val contactState by viewModel.contactState.observeAsState(null)
    contactState?.let {
        NumbersList(dataList = it, event = event)
    }
}

@Composable
fun NumberCard(contact: Contact, event: (ExistContactEvent) -> Unit) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { event(ExistContactEvent.OnItemClick(contact)) }
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
        }
    }
}

@Composable
fun NumbersList(dataList: List<Contact>, event: (ExistContactEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        items(dataList) { number ->
            NumberCard(contact = number, event)
        }
    }
}