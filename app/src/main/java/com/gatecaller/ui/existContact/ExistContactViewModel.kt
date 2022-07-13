package com.gatecaller.ui.existContact

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gatecaller.domain.entity.Contact
import com.gatecaller.domain.repo.INewContactRepo
import com.gatecaller.domain.usecase.ValidateName
import com.gatecaller.domain.usecase.ValidateNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExistContactViewModel @Inject constructor(
    private val newContactRepo: INewContactRepo
) : ViewModel() {

}