package com.gatecaller.ui.newContact

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun NewContactScreen(
    viewModel: NewContactViewModel
) {
    val state = viewModel.state
    Column {
        Text(text = "Имя контакта")
        TextField(
            value = state.name,
            onValueChange = {
                viewModel.onEvent(NewContactEvent.NameChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Введите имя")
            },
            trailingIcon = {
                state.nameError?.let {
                    Icon(
                        Icons.Filled.Error, null,
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        )
        state.nameError?.let {
            Text(
                text = it,
                color = MaterialTheme.colors.error
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Номер")
        TextField(
            value = viewModel.state.number,
            onValueChange = {
                viewModel.onEvent(NewContactEvent.NumberChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Введите номер")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            trailingIcon = {
                state.numberError?.let {
                    Icon(
                        Icons.Filled.Error, null,
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        )
        state.numberError?.let {
            Row(horizontalArrangement = Arrangement.End) {
                Text(
                    text = it,
                    color = MaterialTheme.colors.error
                )
            }
        }
        Button(onClick = { viewModel.onEvent(NewContactEvent.Submit) }) {
            Text(text = "Добавить")
        }
    }

}