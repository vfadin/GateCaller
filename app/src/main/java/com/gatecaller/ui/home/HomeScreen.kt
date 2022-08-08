package com.gatecaller.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gatecaller.R
import com.gatecaller.domain.entity.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    event: (HomeScreenEvent) -> Unit
) {
    val contactState by viewModel.contactState.observeAsState(null)
    val openDialog = remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    title = { Text(stringResource(R.string.contacts)) },
                    actions = {
                        IconButton(onClick = {
                            openDialog.value = true
                        }) {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                        }
                    }
                )
            }) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                if (openDialog.value) {
                    AlertDialog(
                        containerColor = MaterialTheme.colorScheme.background,
                        onDismissRequest = {
                            openDialog.value = false
                        },
                        confirmButton = {
                            Column(
                                modifier = Modifier.padding(all = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = { event(HomeScreenEvent.OnNewContactClick) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                ) {
                                    Text(stringResource(R.string.new_contact))
                                }
                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = { event(HomeScreenEvent.OnExistContactClick) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                ) {
                                    Text(stringResource(R.string.exist_contact))
                                }
                            }
                        }
                    )
                }
                contactState?.let {
                    NumbersList(dataList = it, event)
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

@Preview
@Composable
fun ScreenPreview() {
    val datalist = listOf(
        Contact(0, "+79930206616", "Myself"),
        Contact(0, "+79913289321", "Gate1")
    )
    NumbersList(dataList = datalist, event = { })
}