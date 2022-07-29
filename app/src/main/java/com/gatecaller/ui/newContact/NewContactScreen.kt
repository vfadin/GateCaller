package com.gatecaller.ui.newContact

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gatecaller.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewContactScreen(
    viewModel: NewContactViewModel
) {
    val state = viewModel.state
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
                    title = { Text(stringResource(R.string.new_contact)) },
//                    navigationIcon = { Icon(Icons.Filled.ArrowBack, null) }
                )
            }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.contact_name),
                    modifier = Modifier.padding(16.dp, 4.dp)
                )
                TextField(
                    value = state.name,
                    onValueChange = {
                        viewModel.onEvent(NewContactEvent.NameChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = stringResource(R.string.input_name))
                    },
                    trailingIcon = {
                        state.nameError?.let {
                            Icon(
                                Icons.Filled.Error, null,
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                )
                state.nameError?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(4.dp, 2.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.number),
                    modifier = Modifier.padding(16.dp, 4.dp)
                )
                TextField(
                    value = viewModel.state.number,
                    onValueChange = {
                        viewModel.onEvent(NewContactEvent.NumberChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = stringResource(R.string.input_number))
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    trailingIcon = {
                        state.numberError?.let {
                            Icon(
                                Icons.Filled.Error, null,
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                )
                state.numberError?.let {
                    Row(horizontalArrangement = Arrangement.End) {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(4.dp, 2.dp)
                        )
                    }
                }
                Button(
                    onClick = { viewModel.onEvent(NewContactEvent.Submit) },
                    Modifier
                        .padding(8.dp, 16.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    Text(text = stringResource(R.string.add))
                }
            }
        }
    }
}