package com.gatecaller.ui.newContact

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gatecaller.domain.usecase.ValidateName
import com.gatecaller.domain.usecase.ValidateNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewContactViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(NewContactState())

    private val validateName: ValidateName = ValidateName()
    private val validateNumber: ValidateNumber = ValidateNumber()

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: NewContactEvent) {
        when (event) {
            is NewContactEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }
            is NewContactEvent.NumberChanged -> {
                state = state.copy(number = event.number)
            }
            is NewContactEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val nameValidationResult = validateName.execute(state.name)
        val numberValidationResult = validateNumber.execute(state.number)

        val successfulValidation = listOf(
            nameValidationResult,
            numberValidationResult
        ).all {
            it.successful
        }

        if (!successfulValidation) {
            state = state.copy(
                nameError = nameValidationResult.errorMessage,
                numberError = numberValidationResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
}

sealed class ValidationEvent {
    object Success : ValidationEvent()
}