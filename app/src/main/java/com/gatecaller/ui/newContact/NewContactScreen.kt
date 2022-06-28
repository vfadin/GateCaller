package com.gatecaller.ui.newContact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
            }
        )
        state.nameError?.let { 
            Text(text = it)
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
            )
        )
        state.numberError?.let {
            Text(text = it)
        }
        Button(onClick = { viewModel.onEvent(NewContactEvent.Submit) }) {
            Text(text = "Добавить")
        }
    }

}