package com.gatecaller.ui.home

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gatecaller.R
import com.gatecaller.domain.entity.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberCard(contact: Contact, event: (HomeScreenEvent) -> Unit) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = MaterialTheme.shapes.medium,
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
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = contact.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(0.dp, 4.dp)
            )
            Text(
                text = contact.number,
            )
            DropdownMenu(expanded = isMenuExpanded, onDismissRequest = { isMenuExpanded = false }) {
                DropdownMenuItem(
                    onClick = { event(HomeScreenEvent.OnDeleteClick(contact.id)) },
                    modifier = Modifier.wrapContentWidth(),
                    text = {
                        Text(
                            text = stringResource(R.string.delete),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    },
                    leadingIcon = { Icon(Icons.Filled.Delete, null) }
                )
            }
        }
    }
}
